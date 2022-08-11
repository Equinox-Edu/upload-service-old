FROM gradle:jdk17-focal as cache
RUN mkdir -p /home/gradle/cache
ENV GRADLE_USER_HOME /home/gradle/cache
WORKDIR /home/gradle/app
COPY build.gradle settings.gradle /home/gradle/app/
RUN gradle init -i --stacktrace

FROM gradle:jdk17-focal as builder
COPY --from=cache /home/gradle/cache /home/gradle/.gradle
COPY ./ /opt/app
WORKDIR /opt/app
RUN gradle build

FROM registry.access.redhat.com/ubi8/openjdk-17:1.14-3
WORKDIR /opt/app
COPY --from=builder /opt/app/build/libs/upload-service-*.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","/opt/app/upload-service-*.jar.jar"]