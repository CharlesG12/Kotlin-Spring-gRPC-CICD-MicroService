plugins {
    id("common-conventions")
    id("org.springframework.boot")
    kotlin("plugin.spring")
}

logger.lifecycle("Enabling Kotlin Spring plugin in module ${project.path}")
apply(plugin = "org.jetbrains.kotlin.plugin.spring")

logger.lifecycle("Enabling Spring Boot plugin in module ${project.path}")
apply(plugin = "org.springframework.boot")

logger.lifecycle("Enabling Spring Boot Dependency Management in module ${project.path}")
apply(plugin = "io.spring.dependency-management")

springBoot {
    buildInfo()
}

dependencies {
    // BOM
    implementation(platform("com.linecorp.armeria:armeria-bom:1.20.1"))
    implementation(platform("io.netty:netty-bom:4.1.82.Final"))

    // Spring / Ameria
    implementation("com.linecorp.armeria:armeria-grpc")
    implementation("com.linecorp.armeria:armeria-spring-boot2-webflux-starter")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("net.logstash.logback:logstash-logback-encoder:7.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Test
    testImplementation(kotlin("test"))
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("org.junit.vintage:junit-vintage-engine")
        exclude("org.mockito:mockito-core")
    }
    testImplementation("io.projectreactor:reactor-test:3.4.24")
    testImplementation("io.mockk:mockk:1.13.2")
    testImplementation("com.ninja-squad:springmockk:1.1.0")
}