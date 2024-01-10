plugins {
    id("java")
    id("application")
    id("io.freefair.lombok") version "8.4"
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jboss.weld.se:weld-se-core:5.1.2.Final")
    implementation("org.hibernate:hibernate-core:6.4.1.Final")
    //implementation("com.h2database:h2:2.2.224")
    implementation("org.hsqldb:hsqldb:2.7.2")

    implementation("com.sparkjava:spark-core:2.9.4")
    //implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.16.1")

}

tasks.test {
    useJUnitPlatform()
}

sourceSets {
    main {
        output.setResourcesDir(file("${buildDir}/classes/java/main"))
    }
}

tasks.jar {
    manifest {
        attributes(
                mapOf("Main-Class" to "com.distribuida.Principal",
                        "Class-Path" to configurations.runtimeClasspath
                                .get()
                                .joinToString(separator = " ") { file ->
                                    "${file.name}"
                                })
        )
    }
}