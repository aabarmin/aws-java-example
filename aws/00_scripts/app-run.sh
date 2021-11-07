#!/bin/bash

# Stopping old apps if exist
if [ -d "./pids" ]; then
  echo "Stopping old apps"

  cat ./pids/discovery-server.pid > kill $1
  cat ./pids/challenges-provider.pid > kill $1
  cat ./pids/provider-history.pid > kill $1
  cat ./pids/provider-math.pid > kill $1

  echo "Waiting 2 seconds"
  sleep 2

  echo "==="
fi

# Remove log files
rm -rf ./logs
rm -rf ./pids

mkdir ./logs
mkdir ./pids

# Run discovery server first and wait till it starts
echo "Starting service discovery"
java -jar ./discovery-server.jar &> ./logs/discovery-server.log &
echo $! > ./pids/discovery-server.pid

echo "Waiting till it starts"
EUREKA_URL=http://localhost:8888/eureka
while ! curl --silent -o /dev/null ${EUREKA_URL}; do
  echo "Waiting for Eureka to start at ${EUREKA_URL}"
  sleep 2
done

echo "Starting other apps"
java -jar ./challenges-provider.jar &> ./logs/challenges-provider.log &
echo $! > ./pids/challenges-provider.pid

java -jar ./provider-history.jar &> ./logs/provider-history.log &
echo $! > ./pids/provider-history.pid

java -jar ./provider-math.jar &> ./logs/provider-math.log &
echo $! > ./pids/provider-math.pid

echo "Waiting till main app starts"
MAIN_URL=http://localhost:8080/actuator/health
while ! curl --silent -o /dev/null ${MAIN_URL}; do
  echo "Waiting for the main app to start at ${MAIN_URL}"
  sleep 2
done