plugins {
    id("java")
}

group = "engineer.pol.omnipurpur"
version = "1.19.3-R0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation("redis.clients:jedis:4.3.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}