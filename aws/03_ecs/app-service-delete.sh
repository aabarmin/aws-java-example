#!/bin/bash

aws ecs delete-service --cluster challenges-application \
  --service app-challenges-service