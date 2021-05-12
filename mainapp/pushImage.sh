#!/usr/bin/env bash

echo "Run push image"

imageTag=$1

if [ -z "$1" ]; then
  echo "No image tag provided. Use latest"
  imageTag=latest
fi

repositoryName=453027207759.dkr.ecr.eu-central-1.amazonaws.com/mainapp
imageFullName=$repositoryName:$imageTag

echo "Pushing service app image."

docker push $imageFullName

echo "Image has been pushed. Image: $imageFullName"
