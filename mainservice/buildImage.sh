#!/usr/bin/env bash

echo "RUN build service app"

imageTag=$1

if [ -z "$1" ]; then
  echo "No image tag provided. Use latest"
  imageTag=latest
fi

repositoryName=453027207759.dkr.ecr.eu-central-1.amazonaws.com/mainapp
imageFullName=$repositoryName:$imageTag

echo "Building service app image."

(exec "${BASH_SOURCE%/*}/../gradlew" :service:bootBuildImage --imageName $imageFullName)

echo "Image has been build. Image: $imageFullName"