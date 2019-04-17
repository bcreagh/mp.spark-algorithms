FROM java:9-jre

EXPOSE 4568

RUN mkdir /mp_spark-algos
ADD target/mpspark-1.0-SNAPSHOT-jar-with-dependencies.jar /mp_spark-algos/mpspark-1.0-SNAPSHOT-jar-with-dependencies.jar
ADD entrypoint.sh /mp_spark-algos/entrypoint.sh
WORKDIR /mp_spark-algos

ENTRYPOINT ["./entrypoint.sh"]
