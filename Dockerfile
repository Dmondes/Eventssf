FROM openjdk:23-jdk-oracle AS builder

ARG COMPILE_DIR=/compiledir
WORKDIR ${COMPILE_DIR}

#Copy build files
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src
COPY .mvn .mvn

#Build app
RUN chmod a+x mvnw
RUN ./mvnw clean package -Dmaven.skip.tests=true

#ENTRYPOINT ["java", "-jar", "target/EventAssess-0.0.1-SNAPSHOT.jar"] not required in multi

# second stage Run time
FROM openjdk:23-jdk-oracle

ARG WORK_DIR=/app
WORKDIR ${WORK_DIR}

#Copy jar from builder
COPY --from=builder /compiledir/target/EventAssess-0.0.1-SNAPSHOT.jar buildtoo-final.jar

ENV PORT=8080
EXPOSE ${PORT}

ENTRYPOINT java -jar buildtoo-final.jar
#ENTRYPOINT ["java", "-DSERVER_PORT=${PORT}", "-jar", "buildtoo-final.jar"]

HEALTHCHECK --interval=30s --timeout=30s --start-period=5s --retries=3 \
    CMD curl -s -f http://localhost:${PORT}/demo/health || exit 1