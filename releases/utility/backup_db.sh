#!/bin/sh
################################################################################
## File Details
################################################################################
## NAME:    Backup Production Database
## FILE:    examinprogressboot/releases/utility/backup_prod.sh

################################################################################
## Author Details
################################################################################
## AUTHOR:  Joel Mumo
## CREATED: 26/05/2020

################################################################################
## Description
################################################################################
## This script is used for backing up a database.
##
## Change to the directory where this shell script is and type:  
##     ./backup_prod_db.sh [dev|test|prod]
##
## i.e. to backup dev database type:
##      ./backup_prod_db.sh dev

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
echo args = $#

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
    PSQL_HOST=`getProperty "PSQL_HOST"`
    echo "$COMMENT_PREFIX PSQL_USER = $PSQL_USER"
    echo "$COMMENT_PREFIX PSQL_PASSWORD = $PSQL_PASSWORD"
    echo "$COMMENT_PREFIX PSQL_DB = $PSQL_DB"
fi

################################################################################
## Confirm user wants to backup db
################################################################################
echo $COMMENT_PREFIX "$ENV mode.  Are you sure you wish to continue? ( yes / no )"
read answer

if [ "$answer" != "yes" ]
then
    echo "$COMMENT_PREFIX EXITED"
    exit 1
else
    echo "$COMMENT_PREFIX backing up db for $ENV environment"
    sleep 1
fi

################################################################################
## Backup
################################################################################
## This script is used for backing up the production database
BACKUP_DB="backup_db"
if [ ! -d $BACKUP_DB ]; then
    echo "$COMMENT_PREFIX created $BACKUP_DB directory"
    mkdir $BACKUP_DB
fi

DATE_TIME="`date '+%Y%m%d%H%M%S'`"
FILE_NAME="dbexport_$ENV_$DATE_TIME.pgsql"
PGPASSWORD=$PSQL_PASSWORD pg_dump -h $PSQL_HOST -U $PSQL_USER $PSQL_DB > backup_db/$FILE_NAME
if [ $? -eq 0 ]; then
    echo "$COMMENT_PREFIX Finished backing up db for $ENV environment"
    THIS_DIRECTORY=`pwd`
    LOCATION_FILE=$THIS_DIRECTORY/backup_db/$FILE_NAME
    echo "$COMMENT_PREFIX Location: $LOCATION_FILE"
else
    echo "$COMMENT_PREFIX FAILED backing up db for $ENV environment"
fi
