plugins {
    id("java")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.protobuf)
}

group = "com.github.diogocerqueiralima"
version = "1.0.0"

extra["springGrpcVersion"] = "0.8.0"

repositories {
    mavenCentral()
}

dependencies {

    implementation(project(":common-protos"))
    implementation(libs.postgresql)
    implementation(libs.spring.boot.starter.data.jpa)

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.grpc:spring-grpc-dependencies:${property("springGrpcVersion")}")
    }
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}