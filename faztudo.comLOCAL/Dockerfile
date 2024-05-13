FROM openjdk:17
ENV APP_NAME faztudo
COPY ./target/${APP_NAME}.jar  /app/${APP_NAME}.jar
WORKDIR /app
CMD java -jar ${APP_NAME}.jar
EXPOSE 8080
EXPOSE 3306
