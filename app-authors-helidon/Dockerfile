# docker build -t app-authors .
FROM eclipse-temurin:17.0.10_7-jre-alpine

RUN mkdir /app
WORKDIR /app

COPY build/install/app-authors-helidon/lib .

CMD ["java","-jar","app-authors-helidon.jar"]
