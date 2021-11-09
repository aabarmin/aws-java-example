#!/bin/bash

function ecr_repository_tag() {
  local REPO_NAME=$1
  local REPO_URL=$(aws ecr describe-repositories \
    --query 'repositories[?repositoryName==`'${REPO_NAME}'`].repositoryUri' \
    --output text)

  echo $REPO_URL
}

function ecs_get_task_definition() {
  local TASK_NAME=$1
  local TASK_DEFINITION=$(aws ecs list-task-definitions --family-prefix ${TASK_NAME} \
    --sort desc --query 'taskDefinitionArns[0]')

  TASK_DEFINITION=$(echo ${TASK_DEFINITION} | awk '{print(substr($1, 2, length($1) - 2))}')

  echo $TASK_DEFINITION
}

function ec2_get_subnet() {
    local SUBNET_NAME=$1
    local SUBNET_ID=$(aws ec2 describe-subnets \
      --filter Name=tag:Name,Values=${SUBNET_NAME} --query 'Subnets[0].SubnetId')

    SUBNET_ID=$(echo ${SUBNET_ID} | awk '{print(substr($1, 2, length($1) - 2))}')

    echo $SUBNET_ID
}

function ec2_get_security_group() {
  local GROUP_NAME=$1
  local GROUP_ID=$(aws ec2 describe-security-groups \
    --query 'SecurityGroups[?GroupName==`'$GROUP_NAME'`].GroupId' \
    --output text)

  echo $GROUP_ID
}

function ec2_get_target_group() {
  local GROUP_NAME=$1
  local GROUP_ARN=$(aws elbv2 describe-target-groups \
    --query 'TargetGroups[?TargetGroupName==`'${GROUP_NAME}'`].TargetGroupArn' \
    --output text)

  echo $GROUP_ARN
}