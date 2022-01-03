FROM sanket2494/maven-java

RUN mkdir /root/TDMS-api

WORKDIR /root/TDMS-api/

COPY . .

WORKDIR /root/TDMS-api/server/

RUN ls

RUN cp build/libs/server.jar . && ls

RUN cp build/libs/server-plain.jar .

EXPOSE 1048

CMD ["java", "-jar", "server.jar"]
