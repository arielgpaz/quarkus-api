#!/bin/bash

sudo su
yum update -y
yum install -y docker
service docker start
usermod -a -G docker ec2-user

docker network create quarkus-api-network
docker run -d --name cars_db --network quarkus-api-network -e MYSQL_ROOT_PASSWORD=root_password -e MYSQL_DATABASE=cars_db -e MYSQL_USER=car_driver -e MYSQL_PASSWORD=drive_safety -p 33:3306 -v mysql_data:/var/lib/mysql mysql:latest
docker run -d --name quarkus-api --network quarkus-api-network -p 80:8080 -e DB_USER=car_driver -e DB_PASSWORD=drive_safety -e DB_URL=jdbc:mysql://cars_db:3306/cars_db arielgpaz/quarkus-api:latest