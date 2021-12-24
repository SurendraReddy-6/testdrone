FROM surendra268/maven-java

RUN mkdir /root/tvm-server

WORKDIR /root/tvm-server/

COPY . .

RUN mvn clean install

RUN mv target/vip-0.0.1-SNAPSHOT.jar ./

EXPOSE 8080

CMD ["java", "-jar", "vip-0.0.1-SNAPSHOT.jar"]
