FROM node:11-alpine
MAINTAINER Timon Borter <bbortt.github.io>

WORKDIR /home/node

COPY package*.json .
RUN npm install

COPY .next .

COPY server.js .
COPY next.config.js .
COPY server .
RUN npm run build

EXPOSE 3000

USER node
CMD [ "npm", "start" ]
