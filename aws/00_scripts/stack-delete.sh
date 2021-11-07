#!/bin/bash -xe

# Getting name of the stack
FILE_NAME=$1
if [ -z "${FILE_NAME}" ]; then
  echo "Stack file is not provided, exiting"
  echo "./stack-delete.sh stack-file.yaml"
  exit
fi

STACK_NAME=$(echo $1 | awk '{print(substr($1, 1, index($1, ".") - 1))}')

echo "Checking stack ${STACK_NAME}"

STACK_CREATED=$(aws cloudformation list-stacks \
  --query "StackSummaries[?StackName=='${STACK_NAME}'].CreationTime" \
  --stack-status-filter CREATE_COMPLETE \
  --output text)

if [ -z "${STACK_CREATED}" ]; then
  echo "Stack with name ${STACK_NAME} does not exist"
else
  echo "Removing stack with name ${STACK_NAME}"
  aws cloudformation delete-stack --stack-name $STACK_NAME
fi