FROM surendra268/gradlew-java

RUN mkdir /root/TDMS-api

WORKDIR /root/TDMS-api/

COPY . .

RUN cd server

RUN ls

RUN chmod +x gradlew

RUN gradlew clean build

RUN mv build/libs/server.jar ./

RUN mv build/libs/server-plain.jar ./

RUN nohup java -jar build/libs/server.jar > /tmp/tx-server.log 2>&1 &

EXPOSE 8080

CMD ["java", "-jar", "vip-0.0.1-SNAPSHOT.jar"]
