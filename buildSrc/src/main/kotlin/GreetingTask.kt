import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

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
