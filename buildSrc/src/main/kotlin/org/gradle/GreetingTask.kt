package org.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

// Custom task
abstract class GreetingTask : DefaultTask() {
    @get:Input
    val message: Property<String> = project.objects.property(String::class.java)
            .convention("Hello from Gradle")

    @get:OutputFile
    val outputFile: RegularFileProperty = project.objects.fileProperty()
            .convention(project.layout.buildDirectory.file("output.txt"))

    init {
        group = "custom"
        description = "Writes a greeting to the specified file"
    }

    @TaskAction
    fun greet() {
        outputFile.get().asFile.writeText("${message.get()}\n")
    }
}
