import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
//    id("org.graalvm.buildtools.native") version "0.9.20"
    id("com.google.devtools.ksp") version "1.7.22-1.0.8"
    kotlin("jvm") version "1.7.22"
    kotlin("kapt") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
}

group = "la.shiro"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

val jimmerVersion = "0.7.22"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")

    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.3.Final")

    implementation("org.babyfish.jimmer:jimmer-spring-boot-starter:${jimmerVersion}")
    kapt("org.babyfish.jimmer:jimmer-mapstruct-apt:${jimmerVersion}")
    ksp("org.babyfish.jimmer:jimmer-ksp:${jimmerVersion}")

    implementation ("cn.dev33:sa-token-spring-boot3-starter:1.34.0")
    implementation("net.coobird:thumbnailator:0.4.19")
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

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}