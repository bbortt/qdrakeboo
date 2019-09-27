# Attention: You will have to build the container in [PROJECT ROOT]/
# `$ docker build -f source/edge-gateway/Dockerfile .`
FROM openjdk:11-slim
MAINTAINER Timon Borter <bbortt.github.io>

CMD ["java", "-jar", "edge-gateway.jar"]

RUN useradd -ms /bin/bash gateway

EXPOSE 8080
EXPOSE 8090

USER gateway
WORKDIR /home/gateway

ARG JAR_FILE
ADD source/edge-gateway/build/libs/${JAR_FILE} ./edge-gateway.jar
