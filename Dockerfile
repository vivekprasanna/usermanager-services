FROM openjdk:8

COPY . /code

RUN cd /code \
    && ./gradlew -g clean build --stacktrace \
    && mv ./build/libs/*.jar /app.jar \
    && mv gcp-creds.json /gcp-creds.json \
    && mv Dockerfile /Dockerfile \
    && cd / \
    && sleep 5 \
    && rm -rf /code

ENV PORT=8080

EXPOSE 8080

CMD ["java", "-jar", "/app.jar"]