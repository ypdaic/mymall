FROM daocloud.io/library/java:8u20
VOLUME /tmp
ADD ${JAR_FILE} app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8087

