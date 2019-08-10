# Attention: Because of the local npm dependency (contextful-winston-logger)
# you will have to build the container in [PROJECT ROOT]/
# `$ docker build -f source/federation/federated-user-management/Dockerfile .`
FROM node:12.3-alpine
MAINTAINER Timon Borter <bbortt.github.io>

WORKDIR /home/node

EXPOSE 5000

COPY source/contextful-winston-logger contextful-winston-logger
COPY source/federation/federated-user-management/package*.json ./
RUN sed -i -e 's/file:..\/..\/contextful-winston-logger/file:contextful-winston-logger/g' package*.json \
    && npm install

COPY source/federation/federated-user-management ./

USER node
CMD [ "npm", "start" ]
