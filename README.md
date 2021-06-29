# Welcome to the Starter 

This is a template to help you get started building amazing Kotlin applications and libraries.  Over time, examples
will be compiled as individual modules, that users can pick and choose from when starting their repo.
 
Out of the box, sourdough-kt comes with some really nice tooling 

- Static Analysis using Detekt
- Improved test logging
- Automated git hooks
- Easy test separation using the test set plugin  
- Code coverage via Jacoco
- Preconfigured library publishing to GitHub and Maven Central
- Automated Docker Packaging (TODO)

Currently, there are several example modules included.  These
can be added to or removed via the `settings.gradle.kts` file.

- App: Simple Hello World Kotlin Application
- Api: Bare-bones Ktor application
- Lib: An example library that is imported into both the app and api

Sourdough takes advantage of several new Gradle 7 features.  Dependencies can be found in the `gradle/libs.version.toml` file.  Type safe library and project accessors are on by default.  
