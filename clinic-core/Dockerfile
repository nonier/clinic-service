FROM bellsoft/liberica-openjdk-alpine:18
COPY ./build/libs/clinic-core-0.0.1-SNAPSHOT.jar ./
ENV TZ="Europe/Moscow"
EXPOSE 8080
CMD ["java","-jar", "clinic-core-0.0.1-SNAPSHOT.jar"]