# JetBrains Academy Kotlin Course Template

[![official project](https://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

> **Note**
>
> Click the <kbd>Use this template</kbd> button and clone it in IntelliJ IDEA.

**JetBrains Academy Kotlin course template** is a repository that provides a 
pure template to make it easier to create a new Kotlin course with the [JetBrains Academy
plugin][ref:plugin.marketplace] (check the [Creating a repository from a template][gh:template] article).

The main goal of this template is to speed up the setup phase 
of a Kotlin course development for both new and experienced educators 
by preconfiguring the project scaffold and CI, 
linking to the proper documentation pages, and keeping everything organized.

If you're still not quite sure what this is all about, read our introduction: [What is the JetBrains Academy plugin?][docs:intro]

> **Note**
>
> Click the <kbd>Watch</kbd> button on the top to be notified about releases containing new features and fixes.

### Table of contents

In this README, we will highlight the following elements of template-project creation:
 
- [Getting started](#getting-started)
- [Gradle configuration](#gradle-configuration)

TODO

## Getting started

Before we dive into course development and everything related to it, it's worth mentioning the benefits of using GitHub Templates.
By creating a new project using the current template, you start with no history or reference to this repository.
This allows you to create a new repository easily without copying and pasting previous content, clone repositories, or clearing the history manually.

All you have to do is click the <kbd>Use this template</kbd> button (you must be logged in with your GitHub account).

**TODO: add picture**

[//]: # (After using the template to create your blank project, the [Template Cleanup][file:template_cleanup.yml] workflow will be triggered to override or remove any template-specific configurations, such as the plugin name, current changelog, etc.)

[//]: # ()
[//]: # (Once this is complete, the project is ready to be cloned to your local environment and opened with [IntelliJ IDEA][jb:download-ij].)

The most convenient way for getting your new project from GitHub is the <kbd>Get from VCS</kbd> action available on the Welcome Screen, 
where you can filter your GitHub  repository by its name.

**TODO: add picture**

The next step, after opening your course in IntelliJ IDEA, is to set the proper <kbd>SDK</kbd> to Java in version `17` within the Project Structure settings.

**TODO: add picture**

For the last step, you have to manually review the configuration variables described in the [`gradle.properties`][file:gradle.properties] file and *optionally* move sources from the *org.jetbrains.academy.kotlin.template* package to the one that works best for you.
Then you can get to work implementing your ideas.

## Gradle configuration

The recommended method for Kotlin course development involves using the [Gradle][gradle] setup.

A course built using the JetBrains Academy Kotlin course template includes a Gradle configuration already set up.

**TODO: add details**

### Gradle properties

The project-specific configuration file [`gradle.properties`][file:gradle.properties] contains:

| Property name       | Description                                                   |
|---------------------|---------------------------------------------------------------|
| `courseGroup`       | Package name.                                                 |
| `courseVersion`     | The current version of the course in [SemVer][semver] format. |
| `gradleVersion`     | Version of Gradle used for course development.                |
| `jvmVersion`        | Version of JVM used for course development.                   |

## Course template structure

A generated JetBrains Academy Kotlin Course Template repository contains the following content structure:

```
.
├── .github/                    GitHub Actions workflows
├── .run/                       Predefined Run/Debug Configurations
├── build/                      Output build directory
├── gradle
│   └── wrapper/                Gradle Wrapper
├── common                      Course sources common for all sections
│   └── src
│       └── main
│           ├── kotlin/         Kotlin production sources
│           └── resources/      Resources - images, icons
├── courseSection/              An example of the course section 
│   ├── courseLesson/           An example if the course lesson
│   │   ├── theoryTask/         An example of a theory task
│   │   │   ├── src/            Task sources
│   │   │   │   └── ...            
│   │   │   ├── task.md         Task/theory description
│   │   │   └── task-info.yaml  Task config file
│   │   ├── quizTask/           An example of a quiz task
│   │   │   ├── src/            Task sources
│   │   │   │   └── ...            
│   │   │   ├── task.md         Task/quiz description
│   │   │   └── task-info.yaml  Task config file
│   │   ├── programmingTask/    An example of a programming task
│   │   │   ├── src/            Task sources
│   │   │   │   └── ...            
│   │   │   ├── test/           Task tests
│   │   │   │   └── ...  
│   │   │   ├── task.md         Task description
│   │   │   └── task-info.yaml  Task config file
│   │   └── lesson-info.yaml    Lesson config file
│   ├── courseFrameworkLesson/  An example if the course framework lesson
│   │   ├── ...                 Several examples of lessons
│   │   └── lesson-info.yaml    Lesson config file
│   └── section-info.yaml       Section config file
├── .courseignore               Course ignoring rules
├── .gitignore                  Git ignoring rules
├── build.gradle.kts            Gradle configuration
├── gradle.properties           Gradle configuration properties
├── gradlew                     *nix Gradle Wrapper script
├── gradlew.bat                 Windows Gradle Wrapper script
├── LICENSE                     License, MIT by default
├── README.md                   README
└── settings.gradle.kts         Gradle project settings
```

[gh:template]: https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template

[ref:plugin.marketplace]: https://plugins.jetbrains.com/plugin/10081-jetbrains-academy
[docs:intro]: https://plugins.jetbrains.com/plugin/10081-jetbrains-academy/docs/jetbrains-academy-plugin-faq.html#what_is_the_jetbrains_academy_plugin

[file:gradle.properties]: ./gradle.properties

[gradle]: https://gradle.org

[semver]: https://semver.org