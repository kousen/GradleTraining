# Notes and Other Topics

## File manipulation

- Zip and Copy tasks

Both `Zip` and `Copy` are built-in Gradle `Task` classes.

The GitHub repository at [Gradle_zip_demo](https://github.com/kousen/gradle_zip_demo) includes both a Groovy and a Kotlin module that shows how to create a Zip and a Jar and then unzip or unjar them using a `Copy` task. The associated video is [here](https://youtu.be/najxvbJwTvY?si=P-1N02b11zkU1Vod).

## Version Catalogs

Materials in [Reference docs](https://docs.gradle.org/current/userguide/platforms.html#sec:sharing-catalogs).

TOML file example in the reference manual:
https://docs.gradle.org/current/userguide/platforms.html#sub::toml-dependencies-format

## Inputs and Outputs

Gradle generates a hash for the inputs of each task. When you request a task,
Gradle checks the inputs to see if they've changed and checks to see if the
outputs exist already. If the inputs haven't changed and the outputs exist,
Gradle skips the task.

For each task that we create:

* Specify inputs using `@Input`, `@InputFile` or `@InputDirectory` annotations.
* Specify outputs using `@OutputFile` or `@OutputDirectory` annotations.

## Rich versions DSL

See [this page](https://docs.gradle.org/current/userguide/rich_versions.html) for the complete documentation.

The keywords are:

* `strictly`
* `prefer`
* `require`
* `reject`

Left-square bracket (`[`) means inclusive on the left, but exclusive on the right.

## JUnit parallel tests

See the `maxParallelForks` property in the `Test` task, and set it to a value other than 1.

## Build scans

Run any task with the `--scan` option to generate a build scan.

## Build cache

Use the `--build-cache` option to enable the build cache.

[Here](https://docs.gradle.org/current/userguide/build_cache.html) is the
section of the User Guide on the build cache.

Alternatively, you can add `org.gradle.caching=true` to your `gradle.properties`.
