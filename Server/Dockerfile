FROM openjdk:17-jdk-alpine

# RUN addgroup -S spring && adduser -S spring -G spring
# USER spring:spring

# copy the packaged jar file into our docker image
COPY /build/libs/demo-api.jar /demo.jar

# set the startup command to execute the jar
CMD ["java","-jar","./demo.jar"]