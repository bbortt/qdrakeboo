# Attention: Because of the local npm dependency (contextful-winston-logger)
# you will have to build the container in [PROJECT ROOT]/
# `$ docker build -f source/user-management-api/Dockerfile .`
FROM node:12.3-alpine
MAINTAINER Timon Borter <bbortt.github.io>

WORKDIR /home/node

EXPOSE 5000

COPY source/contextful-winston-logger contextful-winston-logger
COPY source/user-management-api/package*.json ./
RUN sed -i -e 's/file:..\/..\/contextful-winston-logger/file:contextful-winston-logger/g' package*.json \
    && npm install

COPY source/user-management-api ./

USER node
CMD [ "npm", "start" ]
