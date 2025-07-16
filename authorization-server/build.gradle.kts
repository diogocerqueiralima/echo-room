plugins {
    id("java")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
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
    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.thymeleaf)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.boot.starter.oauth2.authorization.server)
    implementation(libs.spring.boot.grpc.starter)
    implementation(libs.passay)

    testImplementation(libs.spring.boot.starter.test)
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