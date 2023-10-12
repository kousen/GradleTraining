import org.gradle.GreetingTask

plugins {
    application
    `project-report`
    `maven-publish`
    alias(libs.plugins.taskinfo)
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()

    maven {
        url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
        credentials {
            username = "user"
            password = "p@ssw0rd"
        }
    }
}

tasks.register<Copy>("unjar") {
    dependsOn(tasks.named<Jar>("jar"))
    from(zipTree(tasks.jar.map(Jar::getArchiveFile)))
    into(layout.buildDirectory.dir("unpacked"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "org.gradle.sample"
            artifactId = "library"
            version = "1.1"

            from(components["java"])
        }
    }
    repositories {
        maven {
            // change to point to your repo, e.g. http://my.org/repo
            url = uri(layout.buildDirectory.dir("repo"))
            credentials {
                username = "user"
                password = project.properties["NEXUS_PASSWORD"] as String? ?: "password"
            }
        }
    }
}

val user: String by extra

tasks.register("hello") {
    doLast {
        println("Hello, $user!")
    }
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is used by the application.
    implementation(libs.google.guava)
    implementation(libs.google.http.client)
    implementation(libs.commons.codec)
    implementation(libs.google.gson)

}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    // Define the main class for the application.
    mainClass.set("com.gradle.lab.App")
}

// "named" is lazy -- only executes if the user asked for this task
// test {

// val test by tasks.getting(Test::class)
//    println("Configuring the Test task")
//    useJUnitPlatform()
//}

// eager instantiation of the task
//task<Test>("anotherTest") {
////    println("Configuring another test")
//    useJUnitPlatform()
//}

// lazy instantiation of the task
tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()

    maxParallelForks = Runtime.getRuntime().availableProcessors() / 2 + 1
}

tasks.register<GreetingTask>("greeting") {
    message.set("Hello from Kotlin")
    outputFile.set(layout.buildDirectory.file("output.txt"))
    doFirst {
        println("About to write to ${outputFile.get().asFile.absolutePath}")
    }
    doLast {
        println("Wrote to ${outputFile.get().asFile.absolutePath}")
    }
}

tasks.register<GreetingTask>("hi")
