#
# maven build ile container içinde jar dosyası oluşturma
#
FROM maven:latest AS builder
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml package -DskipTests

#
# üstte oluşturulan jar dosyasını imaj haline getirme
#
FROM openjdk:11-jre-slim-buster
COPY --from=builder /home/app/target/kafka_consumer.jar /home/app/kafka_consumer.jar
CMD java -jar /home/app/kafka_consumer.jar