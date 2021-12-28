FROM surendra268/gradlew-java

RUN mkdir /root/TDMS-api

WORKDIR /root/TDMS-api/

COPY . .

RUN chmod +x ./server/gradlew

WORKDIR /root/TDMS-api/server/

RUN ./server/gradlew build

RUN mv ./server/build/libs/server.jar ./

RUN mv ./server/build/libs/server-plain.jar ./

RUN nohup java -jar ./server/build/libs/server.jar > /tmp/tx-server.log 2>&1 &

EXPOSE 8080

CMD ["java", "-jar", "vip-0.0.1-SNAPSHOT.jar"]
