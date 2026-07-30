# JetBrains Academy Kotlin Course Template

[![official project](https://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[![Gradle Build](https://github.com/jetbrains-academy/kotlin-course-template/actions/workflows/gradle-build.yml/badge.svg)](https://github.com/jetbrains-academy/kotlin-course-template/actions/workflows/gradle-build.yml)
[![Gradle Build With Detekt](https://github.com/jetbrains-academy/kotlin-course-template/actions/workflows/gradle-build-with-detekt.yml/badge.svg)](https://github.com/jetbrains-academy/kotlin-course-template/actions/workflows/gradle-build-with-detekt.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

> [!NOTE]
> Click the <kbd>Use this template</kbd> button and clone the project in IntelliJ IDEA.

**JetBrains Academy Kotlin course template** is a starter repository designed 
to make it easy to create new Kotlin courses using the [JetBrains Academy
plugin][ref:plugin.marketplace] (see the [Creating a repository from a template][gh:template] article).

The main goal of this template is to speed up the setup phase 
of Kotlin course development for both new and experienced educators. 
It preconfigures the project scaffold and CI, 
links to relevant documentation, and keeps everything organized.

If you aren't sure what this project is about, read our introduction: [What is the JetBrains Academy plugin?][docs:intro]

> [!NOTE]
> Click the <kbd>Watch</kbd> button at the top of the page to receive notifications about new releases, features, and bug fixes.

### Table of contents

In this README, we cover the following aspects of template-project setup:
 
- [Getting started](#getting-started)
- [Gradle configuration](#gradle-configuration)
- [Course info configuration file](#course-info-configuration-file)
- [Course ignore file](#course-ignore-file)
- [Sample code](#sample-code)
- [Adapted inspections](#adapted-inspections)
- [Testing](#testing)
- [Predefined Run/Debug configurations](#predefined-rundebug-configurations)
- [Continuous integration](#continuous-integration)
- [Useful links](#useful-links)

## Getting started

Before diving into course development, note the benefits of using GitHub Templates:
when you create a new repository from this template, it starts fresh with no commit history or direct references to the source repository.
You don't need to copy-paste files, clone repositories, or manually clear history.

All you need to do is click the <kbd>Use this template</kbd> button (ensure you are logged in to your GitHub account).

![Use this template][file:use-template-blur]

The easiest way to open your new project from GitHub is via the <kbd>Get from VCS</kbd> action on the Welcome Screen, 
where you can filter repositories by name.

![Use this template][file:use-this-template.png]


As a final step, review the configuration variables in [`gradle.properties`][file:gradle.properties]. *Optionally*, move the sources from the *org.jetbrains.academy.kotlin.template* package to one that works best for you.
Then, you can get to work and implement your ideas.

## Gradle configuration

We recommend using [Gradle][gradle] for Kotlin course development.

Courses created with this template come with a preconfigured Gradle build setup
that handles base dependencies and plugins. 
For each Gradle module (including individual tasks and shared modules like `common`), 
it configures [JUnit5][ref:junit5] tests, [Kotlin test framework][ref:kotlin.test.framework], and [Detekt][ref:detekt] static analysis. 
It also correctly registers `source` and `test` folders as source and test-source sets in the project.

### Gradle properties

The project configuration file [`gradle.properties`][file:gradle.properties] contains:

| Property name       | Description                                                   |
|---------------------|---------------------------------------------------------------|
| `courseGroup`       | Package name.                                                 |
| `courseVersion`     | The current version of the course in [SemVer][semver] format. |
| `gradleVersion`     | Version of Gradle used for course development.                |
| `jvmVersion`        | Version of the JVM used for course development.               |

## Course template structure

A generated JetBrains Academy Kotlin Course repository uses the following folder layout:

```
.
├── .github/                        GitHub Actions workflows
├── .idea/
│   └── inspectionProfiles/         Adapted inspection files
│       ├── Custom_Inspections.xml  Inspection config
│       ├── profiles_settings.xml   Inspection profile settings
│       └── README.md               Inspection descriptions
├── .run/                           Predefined Run/Debug configurations
├── build/                          Output build directory
├── gradle                          
│   └── wrapper/                    Gradle Wrapper
├── common                          Course sources common for all sections
│   └── src                         
│       └── main                    
│           ├── kotlin/             Kotlin production sources
│           └── resources/          Resources (images, icons)
├── courseSection/                  Example course section 
│   ├── courseLesson/               Example course lesson
│   │   ├── theoryTask/             Theory task example
│   │   │   ├── src/                Task sources
│   │   │   │   └── ...             
│   │   │   ├── task.md             Task/theory description
│   │   │   └── task-info.yaml      Task config file
│   │   ├── quizTask/               An example of a quiz task
│   │   │   ├── src/                Task sources
│   │   │   │   └── ...             
│   │   │   ├── task.md             Task/quiz description
│   │   │   └── task-info.yaml      Task config file
│   │   ├── programmingTask/        Programming task example
│   │   │   ├── src/                Task sources
│   │   │   │   └── ...             
│   │   │   ├── test/               Task tests
│   │   │   │   └── ...             
│   │   │   ├── task.md             Task description
│   │   │   └── task-info.yaml      Task config file
│   │   └── lesson-info.yaml        Lesson config file
│   ├── courseFrameworkLesson/      Framework lesson example
│   │   ├── ...                     Several lesson examples
│   │   └── lesson-info.yaml        Lesson config file
│   └── section-info.yaml           Section config file
├── .courseignore                   Course ignore rules
├── .gitignore                      Git ignore rules
├── build.gradle.kts                Gradle configuration
├── course-info.yaml                Course info configuration file
├── detekt.yml                      Detekt configuration file
├── gradle.properties               Gradle configuration properties
├── gradlew                         *nix Gradle Wrapper script
├── gradlew.bat                     Windows Gradle Wrapper script
├── LICENSE                         License (MIT by default)
├── README.md                       README
└── settings.gradle.kts             Gradle project settings
```

## Course info configuration file

General course metadata (title, language, description, etc.) is configured in the root [course-info.yaml][file:course-info.yaml] file.


```yaml
type: marketplace
title: JetBrains Academy Kotlin course template
language: English
summary: Course description
programming_language: Kotlin
content:
  - courseSection
environment_settings:
  jvm_language_level: JDK_17
```

## Course ignore file

The [.courseignore][file:courseignore] file in the root directory
specifies files and directories that should be excluded from the general course preview or final distributed archive.

```text
README.md
/.run
```

For more details on course previews and distribution, see the documentation for [Course preview][ref:course.preview] 
and [Course distribution][ref:course.distribution].

## Sample code

This template includes a sample course containing one section, two lessons, and five tasks.

![Course structure in the course creator mode][file:course-structure-author]

You can create an arbitrary number of sections, lessons, and tasks. 
Students see a similar view to the creator mode:

![Course structure in the course student mode][file:course-structure-student]

The primary difference lies in framework lessons, where students see 
only task files without intermediate steps.

Learn more about framework lessons in the [Framework Lessons Creation][ref:framework.lessons.creation] section.

> [!NOTE]
> Right-click the root folder and select <kbd>Course Creator</kbd> -> <kbd>Create Course Preview</kbd> to generate a course preview.


The JetBrains Academy plugin supports five distinct task types, 
which can be combined within any lesson (whether regular or framework).
See the [Task][ref:tasks] documentation for details.

## Adapted inspections
The template includes preconfigured IDE inspection settings tailored for learning Kotlin.
They highlight common mistakes that students frequently encounter.
Read [this][file:inspections.readme] README file for details.

If you prefer not to use these inspections, simply delete the [inspectionProfiles][file:inspections] folder.

## Testing

To validate student solutions for programming exercises ([**edu**][ref:tasks] tasks), you need to write tests. 
This repository includes the [Kotlin test framework][ref:kotlin.test.framework] to simplify test creation.
Using the [Java Reflection API][ref:java.reflection.api] under the hood,
the framework allows tests to invoke functions and classes that students have not yet written.
This makes it possible to design exercises without predefined classes or function 
templates, while still properly verifying signatures and behaviour.

Sample tests can be found in `Tests.kt` files
within both the [course lesson][file:course.lesson.tests] and [course framework lesson][file:course.framework.lesson.tests].

For additional examples of using the [Kotlin test framework][ref:kotlin.test.framework], explore these Kotlin courses:

- [Kotlin Onboarding: Introduction][ref:kotlin.onboarding.introduction.marketplace] on [GitHub][ref:kotlin.onboarding.introduction.github]
- [Kotlin Onboarding: Object-Oriented programming][ref:kotlin.onboarding.oop.marketplace] on [GitHub][ref:kotlin.onboarding.oop.github]

## Predefined Run/Debug configurations

The repository includes a `.run` directory containing predefined *Run/Debug configurations* mapped to Gradle tasks:

![Run/Debug configurations][file:run-debug-configurations]

| Configuration name       | Description                                                                    |
|--------------------------|--------------------------------------------------------------------------------|
| Build course             | Runs the `:build` Gradle task with tests only.                                 |
| Build course with detekt | Runs the `:build` task, tests, and [Detekt][ref:detekt] static analysis.       |

## Continuous integration

Continuous integration is handled via [GitHub Actions][gh:actions], automating testing and code verification
so you can focus on authoring content and writing tests.

In the `.github/workflows` directory, you can find the definitions for the following GitHub Actions workflows:
- [Build](.github/workflows/gradle-build.yml)
  - Builds your course
  - Runs all task tests
- [Build with Detekt](.github/workflows/gradle-build-with-detekt.yml)
  - Builds your course
  - Runs all task tests
  - Runs [Detekt][ref:detekt] checks

## Useful links

- [JetBrains Academy plugin][ref:plugin.marketplace]
- [Course creator start guide][ref:course.creator.start.guide]
- [Kotlin test framework][ref:kotlin.test.framework]
- [Courses on Marketplace][ref:marketplace]

[gh:actions]: https://help.github.com/en/actions
[gh:template]: https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template

[ref:marketplace]: https://plugins.jetbrains.com/education
[ref:course.creator.start.guide]: https://plugins.jetbrains.com/plugin/10081-jetbrains-academy/docs/educator-start-guide.html
[ref:plugin.marketplace]: https://plugins.jetbrains.com/plugin/10081-jetbrains-academy
[ref:course.preview]: https://plugins.jetbrains.com/plugin/10081-jetbrains-academy/docs/educator-start-guide.html#preview_course
[ref:course.distribution]: https://plugins.jetbrains.com/plugin/10081-jetbrains-academy/docs/educator-start-guide.html#course_distribution
[ref:framework.lessons.creation]: https://plugins.jetbrains.com/plugin/10081-jetbrains-academy/docs/framework-lessons-guide-for-course-creators.html#a81e8983
[ref:tasks]: https://plugins.jetbrains.com/plugin/10081-jetbrains-academy/docs/framework-lessons-guide-for-course-creators.html#a81e8983
[ref:kotlin.test.framework]: https://github.com/jetbrains-academy/kotlin-test-framework
[ref:java.reflection.api]: https://docs.oracle.com/javase/8/docs/technotes/guides/reflection/index.html
[ref:detekt]: https://github.com/detekt/detekt
[ref:junit5]: https://junit.org/junit5/

[ref:kotlin.onboarding.introduction.marketplace]: https://plugins.jetbrains.com/plugin/21067-kotlin-onboarding-introduction
[ref:kotlin.onboarding.introduction.github]: https://github.com/jetbrains-academy/kotlin-onboarding-introduction
[ref:kotlin.onboarding.oop.marketplace]: https://plugins.jetbrains.com/plugin/21913-kotlin-onboarding-object-oriented-programming
[ref:kotlin.onboarding.oop.github]: https://github.com/jetbrains-academy/kotlin-onboarding-object-oriented-programming

[docs:intro]: https://plugins.jetbrains.com/plugin/10081-jetbrains-academy/docs/jetbrains-academy-plugin-faq.html#what_is_the_jetbrains_academy_plugin

[file:gradle.properties]: ./gradle.properties
[file:course-info.yaml]: ./course-info.yaml
[file:courseignore]: .courseignore
[file:course.lesson.tests]: ./courseSection/courseLesson/programmingTask/test/Tests.kt
[file:course.framework.lesson.tests]: ./courseSection/courseFrameworkLesson/programmingTask/test/Tests.kt
[file:inspections]: ./.idea/inspectionProfiles
[file:inspections.readme]: ./.idea/inspectionProfiles/README.md

[gradle]: https://gradle.org

[semver]: https://semver.org

[file:use-this-template.png]: common/src/main/resources/images/get-from-version-control.png
[file:course-structure-author]: common/src/main/resources/images/course-structure-author.png
[file:course-structure-student]: common/src/main/resources/images/course-structure-student.png
[file:run-debug-configurations]: common/src/main/resources/images/run-debug-configurations.png
[file:use-template-blur]: common/src/main/resources/images/use_template_blur.jpg
