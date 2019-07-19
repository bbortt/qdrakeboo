#!/usr/bin/env bash
# Deployment scripts for Travis-CI.
#
# Usage: `./deploy.sh <build-version>`

if [[ -z $1 ]] ; then
  echo -e "Usage: \`$ ./deploy.sh <build-version>\`

  \t<build-version>\tThe build output version."
  exit 1
fi

docker push qdrakeboo/edge-gateway:$1
docker push qdrakeboo/upstream-server:$1
docker push qdrakeboo/apollo-federation-express-gateway:$1
docker push qdrakeboo/next-js-web-ui:$1
