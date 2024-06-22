FROM ubuntu:latest

# Update package list and install required packages
RUN apt update 
RUN apt install -y openjdk-21-jdk 
RUN apt install -y mysql-server 

# Set environment variables for Java
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH
ENV MYSQL_ROOT_PASSWORD=root



# Create application directory and copy application JAR file
RUN mkdir /app
COPY tc.jar /app/tc.jar
COPY init.sql /app/init.sql

# Set working directory
WORKDIR /app

# Expose port for application
EXPOSE 7000

# Initialize MySQL server and create user with privileges
RUN service mysql start && \
    mysqladmin -u root password 'root' 

RUN sleep 1

RUN service mysql start && \
    mysql -u root -proot < /app/init.sql

RUN echo "init mysql success"
RUN sleep 1
RUN service mysql start \
    &&  mysql -u root -proot -e "CREATE USER 'tc'@'localhost' IDENTIFIED BY 'tc';"
RUN service mysql start \
    &&  mysql -u root -proot -e "GRANT ALL PRIVILEGES ON *.* TO 'tc'@'localhost';"
RUN service mysql start \
    &&  mysql -u root -proot -e "FLUSH PRIVILEGES;"

# Start MySQL service and run Java application
ENTRYPOINT service mysql start && java -jar /app/tc.jar
