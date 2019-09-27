# Attention: Because of the local npm dependency (contextful-winston-logger)
# you will have to build the container in [PROJECT ROOT]/
# `$ docker build -f source/federation/federated-user-management/Dockerfile .`
FROM node:12.3-alpine
MAINTAINER Timon Borter <bbortt.github.io>

WORKDIR /home/node

EXPOSE 5000

COPY source/federation/federated-user-management/dist/federated-user-management.js ./

USER node
CMD [ "node", "federated-user-management" ]
