#!/bin/bash

aws ecs delete-service --cluster challenges-application --force --service app-challenges-provider