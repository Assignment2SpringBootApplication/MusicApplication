FROM openjdk:16
ADD target/MusicApplication-0.0.1-SNAPSHOT.jar MusicApp.jar
ENTRYPOINT ["java", "-jar", "/MusicApp.jar"]