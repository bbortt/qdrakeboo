FROM node:11-alpine
MAINTAINER Timon Borter <bbortt.github.io>

WORKDIR /home/node

COPY package*.json ./
RUN yarn --production

COPY .next ./

EXPOSE 3000

USER node
CMD [ "yarn", "start" ]
