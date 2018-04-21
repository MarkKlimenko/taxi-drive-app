FROM openjdk:alpine
VOLUME /tmp
ADD taxi-drive-app.jar app.jar
RUN sh -c 'touch /app.jar'

// TODO: Get environment variables from secrets or docker run command

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]