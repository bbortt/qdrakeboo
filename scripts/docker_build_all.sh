#!/usr/bin/env bash
# Builds all Docker images. Caution: Depends on `./gradlew build`!
# Must be started from within the root directory!
#
# Usage: `./docker_build_all.sh <tag-name>`

if [[ -z $1 ]] ; then
  echo -e "Usage: \`$ ./docker_build_all.sh <tag-name>\`

  \t<tag-name>\tThe image version tags."
  exit 1
fi

docker build \
  --build-arg JAR_FILE=edge-gateway-$1.jar \
  -t qdrakeboo/edge-gateway:$1 \
  -f source/edge-gateway/Dockerfile .

docker build \
  --build-arg JAR_FILE=upstream-server-$1.jar \
  -t qdrakeboo/upstream-server:$1 \
  -f source/stream/upstream-server/Dockerfile .

docker build \
  -t qdrakeboo/apollo-federation-express-gateway:$1 \
  -f source/federation/apollo-federation-express-gateway/Dockerfile .

docker build \
  -t qdrakeboo/next-js-web-ui:$1 \
  -f source/frontend/next-js-web-ui/Dockerfile .

docker build \
  -t qdrakeboo/user-management-api:$1 \
  -f source/user-management-api/Dockerfile .
