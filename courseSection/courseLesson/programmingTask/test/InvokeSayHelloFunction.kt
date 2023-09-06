import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.Visibility
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable

internal val invokeSayHelloFunction = TestMethod(
    "invokeSayHello",
    TestKotlinType("String"),
    arguments = listOf(
        TestVariable("howManyTimes", "Int"),
    ),
)
