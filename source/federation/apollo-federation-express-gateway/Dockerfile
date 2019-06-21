FROM node:12.3-alpine
MAINTAINER Timon Borter <bbortt.github.io>

WORKDIR /home/node

EXPOSE 4000

COPY package*.json ./

COPY server server
COPY federation-clients.json .
COPY gateway.js .

RUN npm install

USER node
CMD [ "npm", "start" ]
