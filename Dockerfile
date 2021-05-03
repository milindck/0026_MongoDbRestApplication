FROM openjdk:11

# Install Java
#RUN apt-get install -y software-properties-common
#RUN add-apt-repository ppa:webupd8team/java
#RUN apt-get update
#RUN echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
#RUN apt-get install -y git curl oracle-java7-installer

# Install Tomcat
RUN wget https://downloads.apache.org/tomcat/tomcat-9/v9.0.45/bin/apache-tomcat-9.0.45.tar.gz
RUN tar zxvf apache-tomcat-9.0.45.tar.gz
RUN mv apache-tomcat-9.0.45 /opt
RUN ln -s /opt/apache-tomcat-9.0.45 /opt/tomcat

# Install MongoDB
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 7F0CEB10
RUN echo 'deb http://downloads-distro.mongodb.org/repo/ubuntu-upstart dist 10gen' | tee /etc/apt/sources.list.d/10gen.list
RUN dpkg-divert --local --rename --add /sbin/initctl
RUN apt-get update
RUN apt-get install -y mongodb-10gen
RUN mkdir -p /mongodb/db

RUN echo 'mongod --fork --dbpath=/mongodb/db --logpath=/mongodb/mongo.log &' >> /start.sh
RUN echo '/bin/bash /opt/tomcat/bin/startup.sh' >> /start.sh
RUN echo '/bin/bash' >> /start.sh
RUN chmod a+x /start.sh

EXPOSE 27017 8080

CMD ["/start.sh"]


ENTRYPOINT ["/bin/bash"]
