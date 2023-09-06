import org.jetbrains.academy.test.system.core.models.classes.TestClass

internal val mainClass = TestClass(
    classPackage = "org.jetbrains.academy.kotlin.template",
    customMethods = listOf(
        invokeSayHelloFunction,
    ),
)
