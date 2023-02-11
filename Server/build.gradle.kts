plugins {
	id("spring-conventions")
}

dependencies {
	implementation(project(":PROTOS"))
	implementation("io.springfox:springfox-boot-starter:3.0.0")
	compileOnly("io.springfox:springfox-swagger-ui:3.0.0")
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	runtimeOnly("org.postgresql:postgresql:42.5.0")
	runtimeOnly("org.postgresql:r2dbc-postgresql:0.9.2.RELEASE")

	testImplementation("io.r2dbc:r2dbc-h2")
	testRuntimeOnly("com.h2database:h2")
}