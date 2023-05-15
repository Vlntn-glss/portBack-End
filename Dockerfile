FROM amazoncorretto:11-alpine-jdk 
MAINTAINER VI
COPY target/vi-0.0.1-SNAPSHOT.jar vi-app.jar
ENTRYPOINT ["java","-jar","/vi-app.jar"]
