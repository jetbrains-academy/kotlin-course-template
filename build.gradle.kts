import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun gradleProperties(key: String) = providers.gradleProperty(key)

group = gradleProperties("courseGroup").get()
version = gradleProperties("courseVersion").get()

plugins {
    java
    val kotlinVersion = "2.1.0"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
}

val detektReportMerge by tasks.registering(io.gitlab.arturbosch.detekt.report.ReportMergeTask::class) {
    output.set(rootProject.buildDir.resolve("reports/detekt/merge.sarif"))
}

allprojects {
    repositories {
        mavenCentral()
        maven {
            // To be able to use the Kotlin test framework for the tests - https://github.com/jetbrains-academy/kotlin-test-framework
            url = uri("https://packages.jetbrains.team/maven/p/kotlin-test-framework/kotlin-test-framework")
        }
    }
}

tasks {
    wrapper {
        gradleVersion = gradleProperties("gradleVersion").get()
    }
}

// Configure dependencies for all gradle modules
configure(subprojects) {
    apply<io.gitlab.arturbosch.detekt.DetektPlugin>()

    apply {
        plugin("java")
        plugin("kotlin")
    }

    // Configure detekt
    configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
        config = rootProject.files("detekt.yml")
        buildUponDefaultConfig = true
        debug = true
    }
    tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
        finalizedBy(detektReportMerge)
        reports.sarif.required.set(true)
        detektReportMerge.get().input.from(sarifReportFile)
    }
    tasks.getByPath("detekt").onlyIf { project.hasProperty("runDetekt") }

    // Include dependencies
    dependencies {
        // By default, only the core module is included
        implementation("org.jetbrains.academy.test.system:core:2.0.5")
        testImplementation("junit:junit:4.13.2")

        val junitJupiterVersion = "5.9.0"
        implementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
        runtimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
        implementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
        runtimeOnly("org.junit.platform:junit-platform-console:1.9.0")

        val detektVersion = "1.22.0"
        implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektVersion")
        implementation("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")
    }

    val jvmVersion = gradleProperties("jvmVersion").get()
    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = jvmVersion
            }
        }
        withType<JavaCompile> {
            sourceCompatibility = jvmVersion
            targetCompatibility = jvmVersion
        }

        // This part is necessary for the JetBrains Academy plugin
        withType<Test> {
            useJUnitPlatform()

            outputs.upToDateWhen { false }

            addTestListener(object : TestListener {
                override fun beforeSuite(suite: TestDescriptor) {}
                override fun beforeTest(testDescriptor: TestDescriptor) {}
                override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {
                    if (result.resultType == TestResult.ResultType.FAILURE) {
                        val message = result.exception?.message ?: "Wrong answer"
                        val lines = message.split("\n")
                        println("#educational_plugin FAILED + ${lines[0]}")
                        lines.subList(1, lines.size).forEach { line ->
                            println("#educational_plugin$line")
                        }
                        // we need this to separate output of different tests
                        println()
                    }
                }

                override fun afterSuite(suite: TestDescriptor, result: TestResult) {}
            })
        }
    }
}

// We have to store tests inside test folder directly
configure(subprojects.filter { it.name != "common" }) {
    sourceSets {
        getByName("main").java.srcDirs("src")
        getByName("test").java.srcDirs("test")
    }

    dependencies {
        implementation(project(":common"))
    }
}
