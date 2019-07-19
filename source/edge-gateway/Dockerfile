FROM openjdk:11-slim
MAINTAINER Timon Borter <bbortt.github.io>

ENTRYPOINT ["/usr/bin/java", "-jar", "/home/gateway/edge-gateway.jar"]

RUN useradd -ms /bin/bash gateway

EXPOSE 8080
EXPOSE 8090

USER gateway
WORKDIR /home/gateway

ARG JAR_FILE
ADD source/edge-gateway/build/libs/${JAR_FILE} /home/gateway/edge-gateway.jar
