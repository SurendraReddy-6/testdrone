FROM surendra268/gradlew-java

RUN mkdir /root/TDMS-api

WORKDIR /root/TDMS-api/

COPY . .

RUN npm install && npm install Dependencies

WORKDIR /root/TDMS-api/server/

EXPOSE 8080

CMD ["java", "-jar", "vip-0.0.1-SNAPSHOT.jar"]
