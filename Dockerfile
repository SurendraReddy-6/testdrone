FROM ubuntu

RUN mkdir /root/TDMS-api

WORKDIR /root/TDMS-api/

COPY . .

WORKDIR /root/TDMS-api/server/

EXPOSE 8080

CMD ["java", "-jar", "server.jar"]
