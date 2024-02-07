plugins {
    java
    idea
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "io.nozistance"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.jsoup:jsoup:${property("jsoup-version")}")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.telegram:telegrambots:${property("telegrambots-version")}")
    implementation("javax.xml.bind:jaxb-api:${property("jaxb-api-version")}")
    implementation("org.springframework.retry:spring-retry")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
}

idea.module {
    isDownloadJavadoc = true
    isDownloadSources = true
}

tasks.test {
    useJUnitPlatform()
}