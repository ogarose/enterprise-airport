#!/usr/bin/env bash

echo "RUN Push all modules"

imageTag=$1

if [ -z "$1" ]; then
  echo "No image tag provided. Use latest"
  imageTag=latest
fi

bash service/pushImage.sh $imageTag
