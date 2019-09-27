# Attention: You will have to build the container in [PROJECT ROOT]/
# `$ docker build -f source/edge-gateway/Dockerfile .`
FROM openjdk:11-slim
MAINTAINER Timon Borter <bbortt.github.io>

CMD ["java", "-jar", "upstream-server.jar"]

RUN useradd -ms /bin/bash upstream

EXPOSE 8080
EXPOSE 8090

USER upstream
WORKDIR /home/upstream

ARG JAR_FILE
ADD source/stream/upstream-server/build/libs/${JAR_FILE} ./upstream-server.jar
