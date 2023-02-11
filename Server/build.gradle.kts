import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.google.protobuf.gradle.*;

val protobufVersion: String by project
val grpcVersion: String by project
val reactorGrpcVersion: String by project
val protobufPluginVersion : String by project

plugins {
	id("org.springframework.boot") version "2.7.8"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	id("com.google.protobuf") version  "0.8.18"
}



ext["grpcVersion"] = "1.46.0"
ext["grpcKotlinVersion"] = "1.3.0" // CURRENT_GRPC_KOTLIN_VERSION
ext["protobufVersion"] = "3.20.1"

java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {
	group = "com.example"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
		google()
	}
}

dependencies {
	// BOM
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

	// Test
	testImplementation("io.r2dbc:r2dbc-h2")
	testImplementation("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")
	testImplementation("com.ninja-squad:springmockk:4.0.0")
	testImplementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.3")
	testImplementation("io.projectreactor:reactor-test:3.5.2")

//	implementation("org.jetbrains.kotlin:kotlin-reflect")
//	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
//	developmentOnly("org.springframework.boot:spring-boot-devtools")
//	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	testImplementation("io.projectreactor:reactor-test")

	// Grpc
//	implementation("io.grpc:grpc-protobuf:$grpcVersion")
//	implementation("io.grpc:grpc-stub:$grpcVersion")
//	implementation("com.salesforce.servicelibs:reactor-grpc-stub:$reactorGrpcVersion")
//
//	implementation("io.grpc:grpc-kotlin-stub:1.1.0")
//	compileOnly("jakarta.annotation:jakarta.annotation-api:1.3.5")
//	implementation("com.google.protobuf:protobuf-java:$protobufVersion")
//	implementation("net.devh:grpc-server-spring-boot-starter:2.12.0.RELEASE")
//	implementation("net.devh:grpc-client-spring-boot-starter:2.12.0.RELEASE")
//	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
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

//protobuf {
//	protoc{
//		artifact = "com.google.protobuf:protoc:$protobufVersion"
//	}
//	plugins {
//		id("grpc") { artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion" }
//		id("reactorGrpc") { artifact = "com.salesforce.servicelibs:reactor-grpc:$reactorGrpcVersion" }
//	}
//	generateProtoTasks {
//		ofSourceSet("main").forEach {
//			it.plugins {
//				id("grpc")
//				id("reactorGrpc")
//			}
//		}
//	}
//}
