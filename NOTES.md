# Notes and Other Topics

## File manipulation

- Zip and Copy tasks

Both `Zip` and `Copy` are built-in Gradle `Task` classes.

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

## JUnit parallel tests

## DSL for dependency constraints

## Build scans

## Spring Boot builds

## Android builds

## Build cache

Use the `--build-cache` option to enable the build cache.

[Here](https://docs.gradle.org/current/userguide/build_cache.html) is the
section of the User Guide on the build cache.

Alternatively, you can add `org.gradle.caching=true` to your `gradle.properties`.
