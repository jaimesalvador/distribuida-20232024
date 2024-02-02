# docker build -t app-books .
# docker run -d --name books01 -p 8080:8080 app-books

# docker tag app-authors jaimesalvador/app-books
# docker login
# docker build -t jaimesalvador/app-books .
# docker push jaimesalvador/app-books

FROM eclipse-temurin:17.0.10_7-jre-alpine

RUN mkdir /app
WORKDIR /app

COPY build/quarkus-app/app ./app
COPY build/quarkus-app/lib ./lib
COPY build/quarkus-app/quarkus ./quarkus
COPY build/quarkus-app/quarkus-run.jar app.jar

CMD ["java","-jar","app.jar"]
