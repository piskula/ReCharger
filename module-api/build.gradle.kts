plugins {
    id("org.springframework.boot") version "3.1.4" apply false
}

dependencies {
    // rest API annotations
    implementation("org.springframework.boot:spring-boot-starter-web")
    // swagger annotations
    implementation("io.swagger:swagger-annotations:1.6.11")
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}
