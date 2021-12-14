ARG BUILD_IMAGE=adoptopenjdk:11-hotspot
ARG RUNTIME_IMAGE=adoptopenjdk:11-jre-hotspot

FROM $BUILD_IMAGE as builder

WORKDIR /build

COPY .mvn /build/.mvn/
COPY mvnw pom.xml /build/
RUN ./mvnw -B dependency:go-offline

COPY src /build/src
RUN ./mvnw -B package

# Build runtime image
FROM $RUNTIME_IMAGE

COPY --from=builder /build/target/*.jar app.jar
ENV JAVA_OPTS -Xmx64m -Xms64m
EXPOSE 8080
USER 65535:65535
CMD exec java ${JAVA_OPTS} -jar app.jar
