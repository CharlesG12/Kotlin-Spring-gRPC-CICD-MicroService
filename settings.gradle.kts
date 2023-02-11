rootProject.name = "demo"
println("This is executed during the initialization phase.")

include("PROTOS", "Server", "Client")

pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val protobufPluginVersion: String by settings
    val springDepMgmtVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        id("com.google.protobuf") version protobufPluginVersion
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springDepMgmtVersion
    }
}
