#!/bin/bash

aws ecs delete-service --cluster challenges-application --force --service app-challenges-provider
aws ecs delete-service --cluster challenges-application --force --service app-provider-math
aws ecs delete-service --cluster challenges-application --force --service app-provider-history