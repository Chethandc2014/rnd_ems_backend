# syntax=docker/dockerfile:1

# Comments are provided throughout this file to help you get started.
# If you need more help, visit the Dockerfile reference guide at
# https://docs.docker.com/go/dockerfile-reference/

# Want to help us make this template better? Share your feedback here: https://forms.gle/ybq9Krt8jtBL3iCk7

################################################################################

# Create a stage for resolving and downloading dependencies.
FROM eclipse-temurin:8-jdk-focal AS deps

WORKDIR /build

RUN apt-get update && apt-get install -y maven --no-install-recommends && rm -rf /var/lib/apt/lists/*

# Download dependencies.
COPY pom.xml .
RUN mvn dependency:go-offline -DskipTests

################################################################################

# Create a stage for building the application based on the stage with downloaded dependencies.
# This Dockerfile is optimized for Java applications that output an uber jar, which includes
# all the dependencies needed to run your app inside a JVM. If your app doesn't output an uber
# jar and instead relies on an application server like Apache Tomcat, you'll need to update this
# stage with the correct filename of your package and update the base image of the "final" stage
# use the relevant app server, e.g., using tomcat (https://hub.docker.com/_/tomcat/) as a base image.
FROM deps AS package

WORKDIR /build

COPY ./src src/
RUN mvn package -DskipTests && \
    mv target/$(mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout)-$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout).jar target/app.jar

################################################################################

# Create a new stage for running the application that contains the minimal
# runtime dependencies for the application.
FROM eclipse-temurin:8-jre-focal AS final

# Create a non-privileged user that the app will run under.
ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser
USER appuser

# Copy the app jar from the package stage.
COPY --from=package /build/target/app.jar .

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]
