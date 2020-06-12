FROM java:8
ARG JAR_FILE=target/accessholding-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar
COPY sample_input.csv sample_input.csv

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]