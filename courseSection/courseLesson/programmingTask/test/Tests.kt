import org.jetbrains.academy.kotlin.template.main
import org.jetbrains.academy.kotlin.template.newLineSeparator
import org.jetbrains.academy.kotlin.template.runMainFunction
import org.jetbrains.academy.kotlin.template.throwInternalCourseError
import org.jetbrains.academy.test.system.core.invokeWithArgs
import org.jetbrains.academy.test.system.core.models.classes.findClassSafe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Test {
    companion object {
        private lateinit var mainClazz: Class<*>

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            mainClazz = mainClass.findClassSafe() ?: throwInternalCourseError()
        }

        private const val HELLO = "Hello"

        @JvmStatic
        fun invokeSayHelloArguments() = listOf(
            Arguments.of(1, List(1) { HELLO }.joinToString(System.lineSeparator())),
            Arguments.of(2, List(2) { HELLO }.joinToString(System.lineSeparator())),
            Arguments.of(3, List(3) { HELLO }.joinToString(System.lineSeparator()))
        )
    }
    @Test
    fun invokeSayHelloFunction() {
        mainClass.checkMethod(mainClazz, invokeSayHelloFunction)
    }

    @ParameterizedTest
    @MethodSource("invokeSayHelloArguments")
    fun invokeSayHelloImplementation(
        howManyTimes: Int,
        output: String,
    ) {
        val userMethod = mainClass.findMethod(mainClazz, invokeSayHelloFunction)
        Assertions.assertEquals(
            output,
            userMethod.invokeWithArgs(howManyTimes, clazz = mainClazz)?.toString()?.trimIndent(),
            "For howManyTimes = $howManyTimes the function ${invokeSayHelloFunction.name} should return $output"
        )
    }
    @Test
    fun testSolution() {
        Assertions.assertEquals(
            "How many times should I print $HELLO?${newLineSeparator}$HELLO${newLineSeparator}$HELLO".trimIndent(),
            runMainFunction(::main, "2").trimIndent()
        )
    }
}