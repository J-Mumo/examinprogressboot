#!/bin/sh
# This line makes the shell script exit immediately if there is an error
set -e
################################################################################
## File Details
################################################################################
## NAME:    Restoring Production Database
## FILE:    examinprogressboot/releases/utility/restore_prod.sh

################################################################################
## Author Details
################################################################################
## AUTHOR:  Joel Mumo
## CREATED: 26/05/2020

################################################################################
## Description
################################################################################
## This script is used for restoring the production database in the event
## that release_rollback.sql fails

################################################################################
## Global Variables
################################################################################
COMMENT_PREFIX=">>>>>>>>>> "
ENV=dev
PSQL_USER=NOT_SET
PSQL_PASSWORD=NOT_SET
PSQL_DB=NOT_SET

################################################################################
## Functions
################################################################################
function getProperty {
   PROP_KEY=$1
   PROP_VALUE=`cat $PROPERTY_FILE | grep "$PROP_KEY" | cut -d'=' -f2`
   echo $PROP_VALUE
}

################################################################################
## Read properties for environment
################################################################################
if [ $# -eq 0 ]
then
    echo "$COMMENT_PREFIX ERROR"
    echo "$COMMENT_PREFIX Please add argument [dev|test|prod]"
    echo "$COMMENT_PREFIX e.g ./backup_prod_db.sh prod"
    echo "$COMMENT_PREFIX EXITED"
    exit 1
else
    ENV=$1
    PROPERTY_FILE="../properties/$ENV.properties"
    echo "$COMMENT_PREFIX You are running this script in $ENV mode"
    sleep 1
fi

if [ ! -f $PROPERTY_FILE ]
then
    echo "$COMMENT_PREFIX Cannot find properties file, $PROPERTY_FILE"
    echo "$COMMENT_PREFIX EXITED"
    exit 1
else
    PSQL_USER=`getProperty "PSQL_USER"`
    PSQL_PASSWORD=`getProperty "PSQL_PASSWORD"`
    PSQL_DB=`getProperty "PSQL_DB"`
    echo "$COMMENT_PREFIX PSQL_USER = $PSQL_USER"
    echo "$COMMENT_PREFIX PSQL_PASSWORD = $PSQL_PASSWORD"
    echo "$COMMENT_PREFIX PSQL_DB = $PSQL_DB"
fi 

################################################################################
## Ask if have restarted database
################################################################################
echo "$COMMENT_PREFIX This script will attempt to rename the existing database with a name your provide."
echo "$COMMENT_PREFIX To rename the database there must be no connections to the database."
echo "$COMMENT_PREFIX To ensure there are no connections to the database it is advisable you reboot the database."
echo "$COMMENT_PREFIX Do you wish to proceed with the database restore ( yes / no )"
read answer

if [ $answer != "yes" ]
then
    echo "$COMMENT_PREFIX EXITED"
    exit 1
fi

################################################################################
## Read file to restore
################################################################################
ls -ltr backup_db
echo $COMMENT_PREFIX "Paste name of db dump file you wish to restore below (just name, no path)"
read PG_DUMP_FILE

if [ ! -f backup_db/$PG_DUMP_FILE ]
then
    echo $COMMENT_PREFIX "ERROR"
    echo $COMMENT_PREFIX "Cannot find file, $PG_DUMP_FILE"
    echo $COMMENT_PREFIX "EXITED"
    exit 1
fi


# ################################################################################
# ## DROP ANY OPEN SESSIONS TO THE DATABASE
# ################################################################################
echo $COMMENT_PREFIX "Dropping any open connections to $PSQL_DB"
PGPASSWORD=$PSQL_PASSWORD psql -d postgres -U $PSQL_USER -c "SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = '$PSQL_DB'"


################################################################################
## Rename database
################################################################################
PGPASSWORD=$PSQL_PASSWORD psql -d $PSQL_DB -U $PSQL_USER -c '\list'

echo "$COMMENT_PREFIX This script will rename the existing database with a name your provide."  
echo "$COMMENT_PREFIX Please enter a backup name which is not in the list of databases above."
read BACKUP_DATABASE_NAME
psql -d template1 -U $PSQL_USER -c "ALTER DATABASE \"$PSQL_DB\" RENAME TO \"$BACKUP_DATABASE_NAME\""
echo "$COMMENT_PREFIX Database $PSQL_DB renamed to $BACKUP_DATABASE_NAME"

################################################################################
## Recreate Database
################################################################################
PGPASSWORD=$PSQL_PASSWORD createdb --username=$PSQL_USER $PSQL_DB
echo "$COMMENT_PREFIX Recreated database, $PSQL_DB for the user $PSQL_USER"

echo "$COMMENT_PREFIX About to restore the data into the database, $PSQL_DB"
PGPASSWORD=$PSQL_PASSWORD psql -d $PSQL_DB -U $PSQL_USER < backup_db/$PG_DUMP_FILE
echo "$COMMENT_PREFIX Restore successful"

