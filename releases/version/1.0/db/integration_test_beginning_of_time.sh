#!/bin/sh
################################################################################
## File Details
################################################################################
## NAME:    1.0 Beginning of Time
## FILE:    examinprogressboot/releases/version/1.0/db/integration_test_beginning_of_time.sh
## RELEASE: 1.0

################################################################################
## Author Details
################################################################################
## AUTHOR:  Joel Mumo
## CREATED: 21/02/2020

################################################################################
## Description
################################################################################
## This script is used for creating a new database from scratch. It calls all
## previous release.sh

################################################################################
## Global Variables
################################################################################
VERSION=1.0
COMMENT_PREFIX=">>>>>>>>>> "

################################################################################
## Runs only current release - there are no previous releases
################################################################################

if [ $# -eq 0 ]
then
    echo "$COMMENT_PREFIX ERROR"
    echo "$COMMENT_PREFIX Please add argument [dev|test|prod]"
    echo "$COMMENT_PREFIX e.g ./beginning_of_time.sh test"
    echo "$COMMENT_PREFIX EXITED"
    exit 1
else
    ENV=$1
    echo "$COMMENT_PREFIX You are now running version $VERSION of beginning_of_time.sh"
fi

# ./../../1.0/integration_test_beginning_of_time.sh override
./release.sh $ENV override

echo $COMMENT_PREFIX "Finished executing all release scripts to date"

