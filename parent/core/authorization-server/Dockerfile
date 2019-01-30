FROM openjdk:11-slim
MAINTAINER Timon Borter <bbortt.github.io>

ENTRYPOINT ["/usr/bin/java", "-jar", "/home/authorizationserver/authorization-server.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /home/authorizationserver/authorization-server.jar

RUN useradd -ms /bin/bash authorizationserver \
    chown authorizationserver /home/authorizationserver/authorization-server.jar

EXPOSE 8080
EXPOSE 8090

USER authorizationserver
WORKDIR /home/authorizationserver
