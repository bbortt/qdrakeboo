FROM openjdk:11-slim
MAINTAINER Timon Borter <bbortt.github.io>

ENTRYPOINT ["/usr/bin/java", "-jar", "/home/upstream/upstream-server.jar"]

RUN useradd -ms /bin/bash upstream

EXPOSE 8080
EXPOSE 8090

USER upstream
WORKDIR /home/upstream

ARG JAR_FILE
ADD source/stream/upstream-server/build/libs/${JAR_FILE} /home/upstream/upstream-server.jar
