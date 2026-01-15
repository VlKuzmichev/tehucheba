# tehucheba project
FROM eclipse-temurin:11-jre-alpine

LABEL maintainer="echo_vk1@gmail.com"

VOLUME /tmp

EXPOSE 8280

ARG JAR_FILE=target/tehucheba-0.0.1.jar

ADD ${JAR_FILE} tehucheba.jar

ENTRYPOINT ["java", "-jar", "tehucheba.jar"]