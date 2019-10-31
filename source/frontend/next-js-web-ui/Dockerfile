# Attention: Because of local dependencies we build all
# projects from the [PROJECT ROOT]/
# `$ docker build -f source/frontend/next-js-web-ui/Dockerfile .`
FROM nginx:1.17.5-alpine
MAINTAINER Timon Borter <bbortt.github.io>

EXPOSE 4000

COPY source/frontend/next-js-web-ui/out /usr/share/nginx/html
COPY source/frontend/next-js-web-ui/.docker/nginx.conf /etc/nginx/nginx.conf
COPY source/frontend/next-js-web-ui/.docker/site.conf /etc/nginx/conf.d/default.conf

RUN touch /var/run/nginx.pid && \
  chown -R nginx:nginx /var/run/nginx.pid && \
  chown -R nginx:nginx /var/cache/nginx

USER nginx
