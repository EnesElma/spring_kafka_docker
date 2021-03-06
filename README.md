# Usage

### Producer

http://localhost:8080/kafka/{your_text}

### Consumer

http://localhost:8081/consumer/data

# Kafdrop

http://localhost:9000

# Run compose file

``
docker compose up -d
``

# inside of docker-compose.yml
````
version: "3.7"
services:
  kafdrop:
    image: obsidiandynamics/kafdrop
    container_name: kafdrop
    restart: "no"
    ports:
      - "9000:9000"
    networks:
      - kafkaNet
    environment:
      KAFKA_BROKERCONNECT: "broker:19092"
      JVM_OPTS: "-Xms16M -Xmx48M -Xss180K -XX:-TieredCompilation -XX:+UseStringDeduplication -noverify"
    depends_on:
      - broker

  zookeeper:
    image: confluentinc/cp-zookeeper
    container_name: zookeeper
    volumes:
      - kafkaZookeeperVolume:/var/lib/zookeeper/data
      - kafkaZookeeperLogVolume:/var/lib/zookeeper/log
    ports:
      - "2181:2181"
    networks:
      - kafkaNet
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000

  broker:
    image: confluentinc/cp-kafka:latest
    container_name: broker
    volumes:
      - kafkaBrokerVolume:/var/lib/kafka/data
    depends_on:
      - zookeeper
    ports:
    - "9092:9092"
    networks:
      - kafkaNet
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: 'zookeeper:2181'
      KAFKA_ADVERTISED_LISTENERS: 'PLAINTEXT://broker:19092,PLAINTEXT_HOST://localhost:9092,PLAINTEXT_BROKER://broker:19093'
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT,PLAINTEXT_BROKER:PLAINTEXT
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1

  producer:
    build: ./producer
    image: elmaenes/spring_kafka_producer
    container_name: producer
    ports:
    - "8080:8080"
    networks:
      - kafkaNet
    depends_on:
      - broker
    environment:
      MY_BROKER_PRODUCER: broker:19092

  consumer:
    build: ./consumer
    image: elmaenes/spring_kafka_consumer
    container_name: consumer
    ports:
      - "8081:8081"
    networks:
      - kafkaNet
    depends_on:
      - broker
    environment:
      MY_BROKER_CONSUMER: broker:19092


volumes:
  kafkaBrokerVolume:
  kafkaZookeeperVolume:
  kafkaZookeeperLogVolume:

networks:
  kafkaNet:
    name: kafkaNet
    driver: bridge

````
