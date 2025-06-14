import com.google.protobuf.gradle.id

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

    implementation(libs.grpc.services)
    implementation(libs.postgresql)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.grpc.starter)

    testImplementation(libs.spring.boot.grpc.starter.test)
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.grpc:spring-grpc-dependencies:${property("springGrpcVersion")}")
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc") {
                    option("jakarta_omit")
                    option("@generated=omit")
                }
            }
        }
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