FROM openjdk:11
COPY "./target/image-service-api.jar" "image-service.jar"
EXPOSE 7977
ENTRYPOINT ["java", "-jar", "image-service.jar"]