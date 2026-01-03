FROM eclipse-temurin:17-jdk
COPY target/coupon-api.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
