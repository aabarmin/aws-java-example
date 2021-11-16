#!/bin/bash
source ./functions.sh

# Checking if task definitions exist
for i in {1..100}
do
	aws ecs deregister-task-definition --task-definition app-challenges-provider:${i}
	aws ecs deregister-task-definition --task-definition app-provider-math:${i}
	aws ecs deregister-task-definition --task-definition app-provider-history:${i}
done