# Simple REST Service

This is a simple Spring Boot Rest Service for deployment experiments. Nothing fancy, no tests.
Released docker container runs on `linux/amd64`,`linux/arm/v7`,`linux/arm64/v8`.

## Endpoints

```
http://localhost:8080/persons
http://localhost:8080/persons?name=L
http://localhost:8080/persons/{UUID}

http://localhost:8080/environment
```

The environment endpoint is only active when you set the environment variable
`APP_ENABLEENV=true`

## Running in Docker

```bash
docker run -p8080:8080 -e APP_ENABLEENV=true ghcr.io/easimon/simple-rest-service:latest
```
