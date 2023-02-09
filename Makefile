ifndef URL
override URL = http://localhost:8080
endif

imageName := ameria_kotlin_test_api
VERSION := 0.0.1
DOCKER_REPO := charlesdeguo

.PHONY: build
build:
	./gradlew clean build
	docker build -f Dockerfile -t $(imageName):$(VERSION) .

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
	docker build -f Dockerfile -t $(imageName) .

.PHONY: build-docker
build-docker:
	docker build -f Dockerfile -t $(imageName) .

.PHONY: publish-docker
run-docker:
	docker push $(DOCKER_REPO)/$(imageName):$(VERSION)
# 	docker push ${{secrets.DOCKER_USER}}/charlesdeguo