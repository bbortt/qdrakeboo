FROM openjdk:11-slim
MAINTAINER Timon Borter <bbortt.github.io>

ENTRYPOINT ["/usr/bin/java", "-jar", "/home/graphqlapiaccount/graphql-api-account.jar"]

ARG JAR_FILE

RUN useradd -ms /bin/bash graphqlapiaccount \
    chown graphqlapiaccount /home/graphqlapiaccount/graphql-api-account.jar

EXPOSE 8080
EXPOSE 8090

ADD target/${JAR_FILE} /home/graphqlapiaccount/graphql-api-account.jar

USER graphqlapiaccount
WORKDIR /home/graphqlapiaccount
