#!/bin/sh
# This line makes the shell script exit immediately if there is an error
set -e
################################################################################
## File Details
################################################################################
## NAME:    Release
## FILE:    examinprogressboot/releases/version/1.0/db/release.sh
## RELEASE: 1.0

################################################################################
## Author Details
################################################################################
## AUTHOR:  Joel Mumo
## CREATED: 21/02/2020

################################################################################
## Description
################################################################################
## This script is used for updating the database for a Release.

################################################################################
## Global Variables
################################################################################
VERSION=1.0
COMMENT_PREFIX=">>>>>>>>>>"
ENV=dev
PSQL_USER=NOT_SET
PSQL_PASSWORD=NOT_SET
PSQL_DB=NOT_SET

################################################################################
## Functions
################################################################################
getProperty() {
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
    echo "$COMMENT_PREFIX e.g ./release.sh prod"
    echo "$COMMENT_PREFIX EXITED"
    exit 1
else
    ENV=$1
    PROPERTY_FILE="../../../properties/$ENV.properties"
    echo "$COMMENT_PREFIX You are running this script in $ENV mode"
    echo "$COMMENT_PREFIX You are now running version $VERSION of release.sh"
    sleep 1
fi

OVERRIDE_PROMPTS=$2

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
    PSQL_PORT=`getProperty "PSQL_PORT"`
    echo "$COMMENT_PREFIX PSQL_USER = $PSQL_USER"
    echo "$COMMENT_PREFIX PSQL_PASSWORD = $PSQL_PASSWORD"
    echo "$COMMENT_PREFIX PSQL_DB = $PSQL_DB"
    echo "$COMMENT_PREFIX PSQL_HOST = $PSQL_HOST"
    echo "$COMMENT_PREFIX PSQL_PORT = $PSQL_PORT"
fi

if [ -z "$PSQL_USER" ]
then
    echo "$COMMENT_PREFIX Cannot find PSQL_USER in $PROPERTY_FILE"
    echo "$COMMENT_PREFIX EXITED"
    exit 1
fi

if [ -z "$PSQL_USER" ]
then
    echo "$COMMENT_PREFIX Cannot find PSQL_USER in $PROPERTY_FILE"
    echo "$COMMENT_PREFIX EXITED"
    exit 1
fi

if [ -z "$PSQL_DB" ]
then
    echo "$COMMENT_PREFIX Cannot find PSQL_DB in $PROPERTY_FILE"
    echo "$COMMENT_PREFIX EXITED"
    exit 1
fi

################################################################################
## Ask if have backed up database
################################################################################

if [ "$OVERRIDE_PROMPTS" != "override" ]
then 
    echo "$COMMENT_PREFIX You may wish to back up the database. Do you wish to continue? ( yes / no )"
    read answer
    
    if [ "$answer" != "yes" ]
    then
        echo "$COMMENT_PREFIX EXITED"
        exit 1
    else
        echo "$COMMENT_PREFIX Executing scripts on DB"
        sleep 1
    fi
fi

################################################################################
## Execute SQL scripts
################################################################################

echo $COMMENT_PREFIX "Executing 0001_create_tables.sql"
PGPASSWORD=$PSQL_PASSWORD psql --host=$PSQL_HOST  --port=$PSQL_PORT -d $PSQL_DB -U $PSQL_USER -f 0001_create_tables.sql

echo $COMMENT_PREFIX "Executing  0002_standing_data.sql"
PGPASSWORD=$PSQL_PASSWORD psql --host=$PSQL_HOST --port=$PSQL_PORT -d $PSQL_DB -U $PSQL_USER -f 0002_insert_standing_data.sql

echo $COMMENT_PREFIX "Finished executing release SQL"