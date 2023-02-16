FROM openjdk:17
EXPOSE 8082
ADD target/wallet-docker.jar wallet-docker.jar
ENTRYPOINT ["java","-jar","/wallet-docker.jar"]