import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.8"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation(platform("com.linecorp.armeria:armeria-bom:1.20.1"))
	implementation(platform("io.netty:netty-bom:4.1.82.Final"))

	// Spring / Armeria
	implementation("com.linecorp.armeria:armeria-spring-boot2-webflux-starter")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	compileOnly("io.springfox:springfox-swagger-ui:3.0.0")

	implementation("io.springfox:springfox-boot-starter:3.0.0")
	compileOnly("io.springfox:springfox-swagger-ui:3.0.0")
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
//	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	runtimeOnly("org.postgresql:postgresql:42.5.0")
	runtimeOnly("org.postgresql:r2dbc-postgresql:0.9.2.RELEASE")



//	implementation("org.jetbrains.kotlin:kotlin-reflect")
//	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
//	developmentOnly("org.springframework.boot:spring-boot-devtools")
//	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	testImplementation("io.projectreactor:reactor-test")
}

tasks.bootJar {
	archiveFileName.set("demo-api.jar")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
