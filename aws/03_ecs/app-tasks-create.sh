#!/bin/sh

# Get execution role ARN
ROLE_ARN=$(aws iam list-roles --query 'Roles[?RoleName==`fargate_role`].Arn' --output text)

aws ecs register-task-definition --cli-input-json file://./tasks/app-service-discovery.json \
  --execution-role-arn ${ROLE_ARN} \
  --task-role-arn ${ROLE_ARN}