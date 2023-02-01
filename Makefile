ifndef URL
override URL = http://localhost:8080
endif

imageName := demo_api:0.0.1


.PHONY: test
test:
	./gradlew test

.PHONY: run-server
run-server:
	./gradlew bootRun

.PHONY: create-postgres
create-postgres:
	docker-compose -f .local/docker-compose.yaml up -d

.PHONY: teardown-postgres
teardown-postgres:
	docker-compose -f .local/docker-compose.yaml down -v

.PHONY: build-docker
build-docker:
	docker build -t $(imageName) .

.PHONY: run-docker
run-docker:
	docker run -p 8080:8080 -e POSTGRES_HOST=host.docker.internal $(imageName)

.PHONY: build
build:
	./gradlew clean build
	docker build -f Dockerfile -t $(imageName) .