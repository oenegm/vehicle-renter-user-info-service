import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	kotlin("plugin.jpa") version "1.8.22"
}

group = "com.vehiclerenter"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2022.0.4"
val mapstructSpringExtensionsVersion = "1.0.1"
val dataSourceProxyVersion = "1.9.0"
val kotlinLoggingVersion = "5.1.0"
val mapstructVersion = "1.5.5.Final"
val sl4jVersion = "2.0.7"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-hateoas")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	implementation("org.slf4j:slf4j-api:$sl4jVersion")
	implementation("io.github.oshai:kotlin-logging-jvm:$kotlinLoggingVersion")
	implementation("com.github.gavlyukovskiy:datasource-proxy-spring-boot-starter:$dataSourceProxyVersion")
	implementation("org.mapstruct:mapstruct:$mapstructVersion")
	implementation("org.mapstruct.extensions.spring:mapstruct-spring-extensions:$mapstructSpringExtensionsVersion")
	implementation("org.mapstruct.extensions.spring:mapstruct-spring-annotations:$mapstructSpringExtensionsVersion")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
	testAnnotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
	annotationProcessor("org.mapstruct.extensions.spring:mapstruct-spring-extensions:${mapstructSpringExtensionsVersion}")
	testAnnotationProcessor("org.mapstruct.extensions.spring:mapstruct-spring-extensions:${mapstructSpringExtensionsVersion}")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
