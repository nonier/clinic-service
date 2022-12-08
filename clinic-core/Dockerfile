FROM bellsoft/liberica-openjdk-alpine:18
COPY ./build/libs/clinic-core-0.0.1-SNAPSHOT.jar ./
ENV TZ="Europe/Moscow"
CMD ["java","-jar", "clinic-core-0.0.1-SNAPSHOT.jar"]