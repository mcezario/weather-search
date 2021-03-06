# Create accounts to generate the API token
You will need to create accounts in two APIs of weather:
- **Openweathermap**: https://openweathermap.org
  - Open the file `<repository>/src/main/resources/application-prod.properties`
  - Replace [API_KEY] with your generated key of **Openweathermap** at line `openweathermap.key=[API_KEY]`
- **Clima Tempo**: https://advisor.climatempo.com.br/login
  - Open the file `<repository>/src/main/resources/application-prod.properties`
  - Replace [API_KEY] with your generated key of **Clima Tempo** at line `advisor.climatempo.key=[API_KEY]`

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
>
> *** If you want to track the keys in the redis cache, **ensure that redis-commander container is up** and access http://localhost:8081

# Project struture

Structuring of the project inspired by [VaughnVernon - Domain Driven Design](https://github.com/VaughnVernon/IDDD_Samples)
