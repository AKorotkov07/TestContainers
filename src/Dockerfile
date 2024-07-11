FROM openjdk:17-jdk-slim
EXPOSE 8081
ADD build/libs/<название вашего архива>.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]