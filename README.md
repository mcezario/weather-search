# Premise

You must have docker and docker-compose installed.

If you have not installed, more details: https://docs.docker.com/install/#get-started.

## Install mock container

Perform container installation through docker. Run the command below:

`<repository> $ docker-compose -f docker/docker-compose.yml up -d`
OR
`<repository>/docker $ docker-compose up -d`

## Run the application

`<repository> $ mvn spring-boot:run`