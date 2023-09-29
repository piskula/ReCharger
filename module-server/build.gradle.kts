plugins {
    id("org.springframework.boot") version "3.1.4"
    id("com.google.cloud.tools.appengine") version "2.4.4"
    kotlin("plugin.jpa") version "1.8.22" // will e.g. add default constructors to @Entity classes
}

appengine {
    deploy {
        projectId = "GCLOUD_CONFIG"
        version = "GCLOUD_CONFIG"
    }
    stage {
        setArtifact("${buildDir}/libs/${project.name}-${project.version}.jar")
    }
}

springBoot {
    // enable versioning and build info to be injected
    buildInfo()
}

dependencies {
    implementation(project(":module-api"))

    // controller
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // make swagger documentation available
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

    implementation("org.flywaydb:flyway-mysql:9.22.2")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("org.springframework.cloud:spring-cloud-gcp-starter:1.2.8.RELEASE")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}
