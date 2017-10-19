FROM openjdk:alpine
VOLUME /tmp
ADD taxi-drive-app.war app.war
RUN sh -c 'touch /app.war'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.war" ]