FROM node:12.3-alpine
MAINTAINER Timon Borter <bbortt.github.io>

WORKDIR /home/node

COPY package*.json ./

COPY .babelrc .

COPY server.js .

#TODO: Remove this hard configuration link..
COPY next.config.js .

COPY app app
COPY pages pages
COPY server server

RUN npm install \
    && npm run build

EXPOSE 3000

USER node
CMD [ "npm", "start" ]
