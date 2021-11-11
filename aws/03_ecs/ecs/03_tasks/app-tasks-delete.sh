#!/bin/bash
source ./functions.sh

# Checking if task definitions exist
for i in {1..1}
do
	aws ecs deregister-task-definition --task-definition app-challenges-provider:${i}
done