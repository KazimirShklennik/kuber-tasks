#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS package
COPY src /com/epam/postservice/src
COPY pom.xml /com/epam/postservice
RUN mvn -f /com/epam/postservice/pom.xml clean package

#
# run stage
#
FROM bitnami/java:11-prod AS run_package
RUN mkdir -p /opt/postservice/
COPY --from=0 /com/epam/postservice/target/user-post-service-1.0-SNAPSHOT-exec.jar /opt/postservice/user-post-service-1.0-SNAPSHOT-exec.jar
ENV JAVA_OPTS=""
CMD /opt/bitnami/java/bin/java -server ${JAVA_OPTS} -jar /opt/postservice/user-post-service-1.0-SNAPSHOT-exec.jar
