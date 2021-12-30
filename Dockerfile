FROM surendra268/gradlew-java

RUN mkdir /root/TDMS-api

WORKDIR /root/TDMS-api/

COPY . .

WORKDIR /root/TDMS-api/server/

RUN cp build/libs/server.jar .

RUN cp build/libs/server-plain.jar .

EXPOSE 8080

CMD ["java", "-jar", "server.jar"]
