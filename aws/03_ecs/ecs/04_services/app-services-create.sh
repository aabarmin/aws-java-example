#!/bin/bash -xe

source ./functions.sh

TASK_NAME=$(ecs_get_task_definition app-challenges-provider)
SUBNET_A=$(ec2_get_subnet public_a)
SUBNET_B=$(ec2_get_subnet public_b)
GROUP_WEB=$(ec2_get_security_group fargate2_pool_web)
GROUP_ALB=$(ec2_get_security_group alb)

TARGET_GROUP=$(ec2_get_target_group ghost-fargate-target-group)

NETWORK_CONFIG="awsvpcConfiguration={"
NETWORK_CONFIG="${NETWORK_CONFIG}subnets=[${SUBNET_A},${SUBNET_B}],"
NETWORK_CONFIG="${NETWORK_CONFIG}securityGroups=[${GROUP_WEB},${GROUP_ALB}],"
NETWORK_CONFIG="${NETWORK_CONFIG}assignPublicIp=ENABLED}"

LOAD_BALANCER="targetGroupArn=${TARGET_GROUP},"
#LOAD_BALANCER="${LOAD_BALANCER}loadBalancerName=cloudx-resources-load-balancer,"
LOAD_BALANCER="${LOAD_BALANCER}containerName=app-challenges-provider-container,"
LOAD_BALANCER="${LOAD_BALANCER}containerPort=8080"

aws ecs create-service \
    --cluster challenges-application \
    --service-name app-challenges-provider \
    --task-definition ${TASK_NAME} \
    --desired-count 1 \
    --launch-type FARGATE \
    --platform-version LATEST \
    --network-configuration ${NETWORK_CONFIG} \
    --load-balancers ${LOAD_BALANCER}

TASK_NAME=$(ecs_get_task_definition app-provider-math)

aws ecs create-service \
    --cluster challenges-application \
    --service-name app-provider-math \
    --task-definition ${TASK_NAME} \
    --desired-count 1 \
    --launch-type FARGATE \
    --platform-version LATEST \
    --network-configuration ${NETWORK_CONFIG}

TASK_NAME=$(ecs_get_task_definition app-provider-history)

aws ecs create-service \
    --cluster challenges-application \
    --service-name app-provider-history \
    --task-definition ${TASK_NAME} \
    --desired-count 1 \
    --launch-type FARGATE \
    --platform-version LATEST \
    --network-configuration ${NETWORK_CONFIG}