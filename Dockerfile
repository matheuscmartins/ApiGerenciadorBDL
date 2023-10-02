FROM openjdk

WORKDIR /app

COPY target/gerenciador-0.0.1-SNAPSHOT.jar /app/gerenciador.jar

ENTRYPOINT [ "java", "-jar", "gerenciador.jar" ]