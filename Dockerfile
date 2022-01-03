FROM sanket2494/maven-java

RUN mkdir /root/TDMS-api

WORKDIR /root/TDMS-api/

COPY . .

RUN ls

RUN cp server/build/libs/server.jar . 

RUN cp server/build/libs/server-plain.jar . && ls

EXPOSE 1048

CMD ["java", "-jar", "server.jar"]
