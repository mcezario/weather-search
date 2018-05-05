# Premise

You must have docker and docker-compose installed.

If you have not installed, more details: https://docs.docker.com/install/#get-started.

# Install mock(stubmatic) and cache(redis) containers

Perform container installation through docker. Run the command below:

`<repository> $` docker-compose -f docker/docker-compose.yml up -d

OR

`<repository>/docker $` docker-compose up -d

# Run the unit test

`<repository> $` mvn clean test

> **Ensure that stubmatic container is up**.


# Run the application

`<repository> $` mvn spring-boot:run -Dspring-boot.run.profiles=dev
> This profile consists of:
> - Keep cache information in memory through of the Caffeine library.
> - Don't calls the APIs OpenWeatherMap and/or ClimaTempo. The calls are sends to mock(stubmatic).
>
> **Ensure that stubmatic container is up**.

`<repository> $` mvn spring-boot:run -Dspring-boot.run.profiles=prod
> This profile consists of:
> - Keep cache information in redis server.
> - Calls the APIs OpenWeatherMap and/or ClimaTempo.
>
> **Ensure that redis container is up**.