#!/usr/bin/env bash
# Deployment script for Travis CI.
#
# Usage: `./deploy.sh <build-version> <dockerhub-username> <dockerhub-password>`

if [[ -z $1 ]] ; then
  echo -e "Usage: \`$ ./deploy.sh <build-version> <dockerhub-username> <dockerhub-password>\`

  \t<build-version>\tThe build output version.
  \t<dockerhub-username>\tThe username used to authenticate against Docker Hub.
  \t<dockerhub-password>\tThe password used to authenticate against Docker Hub."
  exit 1
fi

echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin

docker push qdrakeboo/edge-gateway:$1
docker push qdrakeboo/upstream-server:$1
docker push qdrakeboo/apollo-federation-express-gateway:$1
docker push qdrakeboo/next-js-web-ui:$1
