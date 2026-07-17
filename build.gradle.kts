plugins {
	java
	id("org.springframework.boot") version "4.1.0"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// COMO MELHORIA, DA PRA GERENCIAR A VERSÃO DAS BIBLIOTECAS POR MEIO DE UMA LIB BOM (Bill Of Materials).
	// ASSIM A VERSÃO NÃO ESTARIA HARDCODED NO BUILD.GRADLE

	// PARA SIMPLIFICAR, UTILIZEI O H2 PARA SIMULAR UM BANCO DE DADOS RELACIONAL.

	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.0.3")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	runtimeOnly("com.h2database:h2:2.1.214")

	testImplementation("org.springframework.boot:spring-boot-starter-actuator-test")
	testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testCompileOnly("org.projectlombok:lombok")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testAnnotationProcessor("org.projectlombok:lombok")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
