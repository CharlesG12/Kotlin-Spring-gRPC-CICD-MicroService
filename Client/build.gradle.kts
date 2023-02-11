val grpcVersion: String by project

plugins {
    id("spring-conventions")
}

dependencies {
    implementation(project(":PROTOS"))
    runtimeOnly("io.projectreactor.netty:reactor-netty:1.0.24")
    runtimeOnly("io.grpc:grpc-netty:$grpcVersion")
    implementation(kotlin("script-runtime"))
}