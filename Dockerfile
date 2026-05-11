FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY backend/ ./backend/
WORKDIR /app/backend
RUN ./mvnw -q -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/backend/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
