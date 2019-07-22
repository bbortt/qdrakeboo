#!/usr/bin/env bash
# Deployment script for Travis CI.
#
# Usage: `./deploy.sh <tag-name>`

if [[ -z $1 ]] ; then
  echo -e "Usage: \`$ ./deploy.sh <tag-name>\`

  \t<tag-name>\tThe images to be tagged with."
  exit 1
fi

docker push qdrakeboo/edge-gateway:$1
docker push qdrakeboo/upstream-server:$1
docker push qdrakeboo/apollo-federation-express-gateway:$1
docker push qdrakeboo/next-js-web-ui:$1
docker push qdrakeboo/user-management-api:$1
