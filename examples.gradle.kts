plugins {
    application
    `project-report`
    alias(libs.plugins.taskinfo)
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
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
task<Test>("anotherTest") {
//    println("Configuring another test")
    useJUnitPlatform()
}

// lazy instantiation of the task
tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
//    println("Configuring the Test task")
    useJUnitPlatform()
}

tasks.register<GreetingTask>("greeting") {
    message = "Hello from Kotlin"
    outputFile.set(layout.buildDirectory.file("output.txt"))
    doFirst {
        println("About to write to ${outputFile.get().asFile.absolutePath}")
    }
    doLast {
        println("Wrote to ${outputFile.get().asFile.absolutePath}")
    }
}

tasks.register<GreetingTask>("hi")

// Custom task
abstract class GreetingTask : DefaultTask() {
    @get:Input
    abstract val message: Property<String>

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    init {
        group = "custom"
        description = "Writes a greeting to the specified file"
        message.convention("Hello from Gradle")
        outputFile.convention(
                project.layout.buildDirectory.file("output.txt")
        )
    }

    @TaskAction
    fun greet() {
        outputFile.get().asFile.writeText("${message.get()}\n")
    }
}
