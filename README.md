# Simple REST Service

This is a simple Spring Boot Rest Service for deployment experiments. Nothing fancy, no tests.
Released docker container runs on `linux/amd64`,`linux/arm/v7`,`linux/arm64/v8`.

## Endpoints

```
# deployment info
http://localhost:8080/

# persons (consisting of a id (UUID) and name (String))
http://localhost:8080/persons
http://localhost:8080/persons?name=L
http://localhost:8080/persons/{UUID}

# simple health endpoint
http://localhost:8080/health

# simple CPU load generator, executes single thread doing a busy wait
add new CPU load to the queue: http://localhost:8080/load/new?secs=100
see remaining run time in the queue: http://localhost:8080/load

# all environment variables (beware, may contain sensitive information)
http://localhost:8080/environment
```

The environment endpoint is only active when you set the environment variable
`APP_ENVIRONMENT_ENDPOINT_ENABLED=true`. Sensitive variables can be filtered by
setting `APP_ENVIRONMENT_ENDPOINT_FILTERED_PREFIXES=PREFIX1_,PREFIX2_` to filter
variable names that begin with `PREFIX1_` or `PREFIX_2`.
For defaults see [application.yaml](./src/main/resources/application.yaml).

## Running in Docker

```bash
docker run -p8080:8080 -e APP_ENABLEENV=true ghcr.io/easimon/simple-rest-service:latest
```
