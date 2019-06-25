# Attention: Because of the local npm dependency (contextful-winston-logger)
# you will have to build the container in [PROJECT ROOT]/
# `$ docker build -f source/frontend/next-js-web-ui/Dockerfile .`
FROM node:12.3-alpine
MAINTAINER Timon Borter <bbortt.github.io>

WORKDIR /home/node

EXPOSE 3000

COPY source/contextful-winston-logger contextful-winston-logger
COPY source/frontend/next-js-web-ui/package*.json ./
RUN sed -i -e 's/file:..\/..\/contextful-winston-logger/file:contextful-winston-logger/g' package*.json \
    && npm install

COPY source/frontend/next-js-web-ui ./

RUN npm run build

USER node
CMD [ "npm", "start" ]
