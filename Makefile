ifndef URL
override URL = http://localhost:8080
endif

imageName := ameria_kotlin_test_api:0.0.1

.PHONY: build
build:
	./gradlew clean build
    docker build -f Dockerfile -t $(imageName) .

.PHONY: test
test:
	./gradlew test

.PHONY: run-server
run-server:
	./gradlew bootRun

.PHONY: build-docker
build-docker:
	docker build -f Dockerfile -t $(imageName) .

.PHONY: publish-docker
publish-docker:
	docker push $(imageName)

.PHONY: run-docker
run-docker:
	docker run -p 8080:8080 -e POSTGRES_HOST=host.docker.internal $(imageName)

.PHONY: create-postgres
create-postgres:
	docker-compose -f .local/docker-compose.yaml up -d

.PHONY: teardown-postgres
teardown-postgres:
	docker-compose -f .local/docker-compose.yaml down -v