#!/usr/bin/env bash

echo "RUN build all modules"

imageTag=$1

if [ -z "$1" ]; then
  echo "No image tag provided. Use latest"
  imageTag=latest
fi

bash mainservice/buildImage.sh $imageTag
