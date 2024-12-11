plugins {
	java
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "ca.gbc"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.3")
	}
}

dependencies {
	// Core Spring Boot starters
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Flyway for database migrations
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")
	implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j:3.2.0")

	// Spring Cloud OpenFeign for REST client
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

	// Spring Cloud Contract Stub Runner (for testing purposes)
	implementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner")

	// Lombok for reducing boilerplate code
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// Development tools
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// PostgreSQL database
	runtimeOnly("org.postgresql:postgresql")

	//swagger & kafka
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
	testImplementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.6.0")
	implementation("org.springframework.kafka:spring-kafka:3.3.0")
	testImplementation("org.springframework.kafka:spring-kafka-test:3.3.0")
	testImplementation("org.testcontainers:kafka:1.20.4")

	// Test dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:postgresql")
	testImplementation("io.rest-assured:rest-assured")
	testImplementation("com.github.tomakehurst:wiremock:2.27.2") // For mocking HTTP services in tests


	// Optional JUnit Platform test implementation
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform() // Use JUnit Platform for testing
}

// Ensure that any custom Feign or Spring Cloud configurations, if needed, are added to your application properties