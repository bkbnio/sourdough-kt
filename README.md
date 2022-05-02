# Welcome to the Starter

This is a template to help you get started building amazing Kotlin applications and libraries. Over time, examples will
be compiled as individual modules, that users can pick and choose from when starting their repo.

## Table of Contents

1. [Modules](#modules)
    1. [API](#api)
    2. [App](#app)
    3. [CLI](#cli)
    4. [Lib](#lib)
    5. [Persistence](#persistence)
2. [Tooling](#tooling)
   1. [Gradle](#gradle)

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

### Persistence

Lightweight persistence module leveraging Flyway for migrations along with Kotlin Exposed as the ORM.  Out of the box it 
is configured to point directly at the postgres instance defined in the docker compose file in the root of the project.

## Tooling

### Gradle

This library leverages the [sourdough-gradle](https://github.com/bkbnio/sourdough-gradle) collection of plugins to autoconfigure a majority of the boilerplate
necessary to get this repo up and running. For more information on all the setup that plugin does, please refer to its
documentation