plugins {
    id("java")
    id("application")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("io.helidon:helidon-dependencies:3.2.6"))

    implementation("io.helidon.integrations.cdi:helidon-integrations-cdi-datasource-hikaricp")
    implementation("io.helidon.microprofile.server:helidon-microprofile-server")
    implementation("org.glassfish.jersey.media:jersey-media-json-binding")

    runtimeOnly("jakarta.persistence:jakarta.persistence-api")
    runtimeOnly("jakarta.transaction:jakarta.transaction-api")

    runtimeOnly("io.helidon.integrations.cdi:helidon-integrations-cdi-datasource-hikaricp")
    runtimeOnly("io.helidon.integrations.cdi:helidon-integrations-cdi-hibernate")
    runtimeOnly("io.helidon.integrations.cdi:helidon-integrations-cdi-jta-weld")
    runtimeOnly("io.helidon.integrations.cdi:helidon-integrations-cdi-jpa")

    implementation("org.postgresql:postgresql:42.7.2")
    implementation("org.hibernate:hibernate-core:6.4.4.Final")

    implementation("org.jboss:jandex:3.1.6")
}

tasks.test {
    useJUnitPlatform()
}

sourceSets {
    main {
        output.setResourcesDir( file("${buildDir}/classes/java/main") )
    }
}

tasks.jar {
    manifest {
        attributes(
                mapOf("Main-Class" to "com.distribuida.authors.Main",
                        "Class-Path" to configurations.runtimeClasspath
                                .get()
                                .joinToString(separator = " ") { file ->
                                    "${file.name}"
                                })
        )
    }
}
