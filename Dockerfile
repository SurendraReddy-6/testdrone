FROM ubuntu
ARG DEBIAN_FRONTEND=noninteractive
RUN apt-get update
RUN apt-get install apache2 -y
RUN apt-get install openjdk-8-jdk -y
RUN gradlew clean install
ENTRYPOINT ["/usr/sbin/apache2ctl"]
EXPOSE 80
CMD ["-D","FOREGROUND"]
