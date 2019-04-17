FROM java:9-jre

EXPOSE 4567

RUN mkdir /mp_spark-base
ADD target/mpspark-1.0-SNAPSHOT-jar-with-dependencies.jar /mp_spark-base/mpspark-1.0-SNAPSHOT-jar-with-dependencies.jar
ADD entrypoint.sh /mp_spark-base/entrypoint.sh
WORKDIR /mp_spark-base

ENTRYPOINT ["./entrypoint.sh"]
