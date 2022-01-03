FROM sanket2494/maven-java

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update

RUN apt-get install apache2 -y

RUN ls

COPY ./server/react/build/. /var/www/html/. 

ENTRYPOINT ["usr/sbin/apache2ctl"]

EXPOSE 80

CMD ["-D","FOREGROUND"]
