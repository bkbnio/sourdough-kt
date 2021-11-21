# Welcome to the Starter

This is a template to help you get started building amazing Kotlin applications and libraries. Over time, examples will
be compiled as individual modules, that users can pick and choose from when starting their repo.

## Table of Contents

1. [Modules](#modules)
    1. [API](#api)
    2. [App](#app)
    3. [CLI](#cli)
    4. [Lib](#lib)
2. [Tooling](#tooling)
3. [Gradle](#gradle)
    1. [Approach](#approach)
    2. [Plugins](#plugins)

## Modules

Sourdough aims to provide you with a starter point for any type of JVM based Kotlin application you choose to build.

Certain information on module approach will also be detailed in the [gradle](#gradle) section, as there is a lot of
cross over between the modules and the necessary Gradle configuration.

### API

Bare bones [Ktor](https://ktor.io) API server.

### App

Simple application to run background processes.

### CLI

[Kotlinx CLI](https://github.com/Kotlin/kotlinx-cli) Demo.

### Lib

Demo of the `java-library` plugin, allowing code to be packaged into a distributable for reuse in other applications /
modules

## Tooling

### Gradle

Gradle is used extensively to provide the out-of-the-box configuration for each of the respective modules in this
template.

#### Approach

Gradle has a _ton_ of features. Perhaps too many. Definitely too many. With that said, this template leverages several
of those features to try to provide a sane, flexible starting point that will serve any type of JVM based application
you choose to build.

For conventions that are used across modules, there is a `buildSrc` folder that contains a number of convention plugins
that can be reused in each module. At the moment, there is a base configuration along with explicit `library`
and `application` conventions.

Use them in accordance with your needs.

For module level overrides, this template uses a version catalog that can be found in the `settings.gradle.kts` file.

This allows typesafe access to plugins and dependencies. Unfortunately, due to some intricacies in the way that Gradle
builds, these accessors cannot be use in the `buildSrc`.

#### Plugins

This template leverages several of the amazing community plugins to provide an awesome experience out of the box.

- [Detekt](https://detekt.github.io/detekt/) for static analysis
- [Test Logger](https://github.com/radarsh/gradle-test-logger-plugin) for improved test output
- [Task Tree](https://github.com/dorongold/gradle-task-tree) for easily triaging which gradle tasks are performed during
  a given task
- [Jacoco](https://docs.gradle.org/current/userguide/jacoco_plugin.html) for test coverage (will eventually be replaced
  with Kover)
- [Dokka](https://github.com/Kotlin/dokka) TODO
- [Git Hooks](https://github.com/jakemarsden/git-hooks-gradle-plugin) to perform gradle tasks ahead of version control
  operations
- [Nexus Publishing](https://github.com/gradle-nexus/publish-plugin) to push libraries to Maven Central
