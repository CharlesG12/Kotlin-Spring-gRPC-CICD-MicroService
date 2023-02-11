FROM openjdk:17-jdk-alpine

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# copy the packaged jar file into our docker image
COPY */build/libs/Server.jar .

# set the startup command to execute the jar
CMD ["java","-jar","/Server.jar"]