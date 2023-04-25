#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS package
COPY src /com/epam/userservice/src
COPY pom.xml /com/epam/userservice
RUN mvn -f /com/epam/userservice/pom.xml clean package

#
# run stage
#
FROM bitnami/java:11-prod AS run_package
RUN mkdir -p /opt/userservice/
COPY --from=0 /com/epam/userservice/target/user-service-1.0-SNAPSHOT-exec.jar /opt/userservice/user-service-1.0-SNAPSHOT-exec.jar
ENV JAVA_OPTS=""
CMD /opt/bitnami/java/bin/java -server ${JAVA_OPTS} -jar /opt/userservice/user-service-1.0-SNAPSHOT-exec.jar
