FROM ubuntu
ARG DEBIAN_FRONTEND=noninteractive
RUN apt-get update
RUN apt-get install apache2 -y
ENTRYPOINT ["/usr/sbin/apache2ctl"]
EXPOSE 80
CMD ["-D","FOREGROUND"]
