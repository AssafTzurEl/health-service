# Health Service

This project is intended to demonstrate Kubernetes' health features.

## Command-line arguments

`--delay <duration>`
Delays execution by sleeping for the specified duration in milliseconds. 

## Usage

### In Kubernetes YAML

Set K8s to monitor the following probes:
```
http://health-service/status/ready
```
and
```
http://health-service/status/healthy
```

### Getting current status

Liveness:
```shell
$ curl http://localhost:8080/status/healthy
$ curl http://localhost:8080/actuator/health/liveness
```

Readiness:
```shell
$ curl http://localhost:8080/status/ready
$ curl http://localhost:8080/actuator/health/readiness
```

### Setting current status

Liveness:
```shell
$ curl -X PUT http://localhost:8080/status/healthy?status=false
```

Readiness:
```shell
$ curl -X PUT http://localhost:8080/status/ready?status=false
```

(works with `status=true` too, of course).

## OpenAPI/Swagger support

Open http://localhost:8080/swagger