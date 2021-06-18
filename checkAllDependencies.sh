#!/usr/bin/env bash

echo "Check dependency of All projects"
(exec "${BASH_SOURCE%/*}/gradlew" dependencyUpdates)