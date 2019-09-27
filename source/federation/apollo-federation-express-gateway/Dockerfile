# Attention: Because of the local npm dependency (contextful-winston-logger)
# you will have to build the container in [PROJECT ROOT]/
# `$ docker build -f source/federation/apollo-federation-express-gateway/Dockerfile .`
FROM node:12.3-alpine
MAINTAINER Timon Borter <bbortt.github.io>

WORKDIR /home/node

EXPOSE 4000

COPY source/federation/apollo-federation-express-gateway/dist/apollo-federation-express-gateway.js ./

USER node
CMD [ "node", "apollo-federation-express-gateway" ]
