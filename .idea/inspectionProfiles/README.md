# Inspection descriptions
This README file contains descriptions of all adapted inspections in the [config](./Custom_Inspections.xml) file.

> [!NOTE]
> This file, as well as the config file, contains only the most common inspections that have been adapted for a better learning experience.
> For a complete list of inspections available in the IntelliJ platform, see the Kotlin Inspections tab (<kbd>Settings</kbd> -> <kbd>Editor</kbd> -> <kbd>Inspections</kbd> -> <kbd>Kotlin</kbd>).

The `Severity` field (`level` in the config file) indicates how inspections will be displayed in the top-right corner of the editor and in the **Problems** tab. Some of the possible values are:

| Name         | Config name  | Example                                                                                                                                             |
|--------------|--------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|
| Error        | ERROR        | <img width="30" alt="image" src="https://github.com/jetbrains-academy/kotlin-course-template/assets/55441714/6d9c1a07-b4ac-418e-88cd-15c02d14c77d"> |
| Warning      | WARNING      | <img width="30" alt="image" src="https://github.com/jetbrains-academy/kotlin-course-template/assets/55441714/eebab895-cc66-485f-804a-ccdafac1134b"> |
| Weak Warning | WEAK WARNING | <img width="30" alt="image" src="https://github.com/jetbrains-academy/kotlin-course-template/assets/55441714/2d8d6606-1ab5-4e14-86f9-1d4278a973a1"> |

The `Highlighting` field (`editorAttributes` in the config file) indicates how the inspection will be highlighted in the IDE. Some of the possible values are:

| Name          | Config name                   | Example                                                                                                                                              |
|---------------|-------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------|
| Error         | ERRORS_ATTRIBUTES             | <img height="33" alt="image" src="https://github.com/jetbrains-academy/kotlin-course-template/assets/55441714/6c80ba5f-fef6-4787-9d02-e5c9f35c30a4"> |
| Warning       | WARNING_ATTRIBUTES            | <img height="33" alt="image" src="https://github.com/jetbrains-academy/kotlin-course-template/assets/55441714/e3a04c4a-2a82-43c9-9025-9213876a25e4"> |
| Weak Warning  | INFO_ATTRIBUTES               | <img height="33" alt="image" src="https://github.com/jetbrains-academy/kotlin-course-template/assets/55441714/3ceaa4fd-0f60-4928-beee-1e367faf1670"> |
| Strikethrough | MARKED_FOR_REMOVAL_ATTRIBUTES | <img height="33" alt="image" src="https://github.com/jetbrains-academy/kotlin-course-template/assets/55441714/cf6e6d08-202b-43ff-96ab-a69138fb1370"> |

> [!NOTE]  
> To alter the config file, use the IDE **Kotlin Inspections** settings tab, where you can enable/disable inspections and choose theirs severity and highlighting styles.

Below are detailed descriptions of all inspections included in the current configuration file.

## AddOperatorModifier

**Severity**: Weak Warning </br>
**Highlighting**: Weak Warning </br>

<details>
<summary>Function should have 'operator' modifier</summary>

Reports a function that matches one of the Kotlin operator conventions but lacks the `operator` keyword.
Adding the `operator` modifier allows function consumers to write idiomatic Kotlin code.

**Example:**

```kotlin
class Complex(val real: Double, val imaginary: Double) {
    fun plus(other: Complex) =
        Complex(real + other.real, imaginary + other.imaginary)
}

fun usage(a: Complex, b: Complex) {
    a.plus(b)
}
```

Applying the quick-fix adds the `operator` modifier keyword:

```kotlin
class Complex(val real: Double, val imaginary: Double) {
    operator fun plus(other: Complex) =
        Complex(real + other.real, imaginary + other.imaginary)
}

fun usage(a: Complex, b: Complex) {
    a + b
}
```

</details>

## AddVarianceModifier

**Severity**: Weak Warning </br>
**Highlighting**: Weak Warning </br>

<details>
<summary>Type parameter can have 'in' or 'out' variance</summary>

Reports type parameters that can have `in` or `out` variance.
Using `in` and `out` variance provides more precise type inference in Kotlin and clearer code semantics.

**Example:**

```kotlin
class Box<T>(val obj: T)

fun consumeString(box: Box<String>) {}
fun consumeCharSequence(box: Box<CharSequence>) {}

fun usage(box: Box<String>) {
    consumeString(box)
    consumeCharSequence(box) // Compilation error
}
```

Applying the quick-fix adds the matching variance modifier:

```kotlin
class Box<out T>(val obj: T)

fun consumeString(box: Box<String>) {}
fun consumeCharSequence(box: Box<CharSequence>) {}

fun usage(box: Box<String>) ++{
    consumeString(box)
    consumeCharSequence(box) // OK
}
```

</details>

## CanBeParameter

**Severity**: Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Constructor parameter is never used as a property</summary>

Reports primary constructor parameters where `val` or `var` can be safely removed.

Class properties declared in the constructor increase memory consumption.
If the parameter value is only used in the constructor, you can omit the property declaration.

Note that the referenced object might be garbage-collected earlier.

**Example:**

```kotlin
class Task(val name: String) {
    init {
        print("Task created: $name")
    }
}
```

Applying the quick-fix removes the redundant `val` or `var` keyword:

```kotlin
class Task(name: String) {
    init {
        print("Task created: $name")
    }
}
```

</details>

## CanBePrimaryConstructorProperty

**Severity**: Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Property is explicitly assigned to constructor parameter</summary>

Reports properties that are explicitly assigned to primary constructor parameters.
Declaring properties directly inside the primary constructor reduces boilerplate code and improves readability.

**Example:**

```kotlin
class User(name: String) {
    val name = name
}
```

Applying the quick-fix consolidates the parameter and property declaration into a primary constructor parameter:

```kotlin
class User(val name: String) {
}
```

</details>

## CanBeVal

**Severity**: Error </br>
**Highlighting**: Error </br>

<details>
<summary>Local 'var' is never modified and can be declared as 'val'</summary>

Reports local variables declared with `var` that are never modified.
Kotlin encourages declaring immutable variables using the `val` keyword to ensure their value cannot change.

**Example:**

```kotlin
fun example() {
    var primeNumbers = listOf(1, 2, 3, 5, 7, 11, 13)
    var fibonacciNumbers = listOf(1, 1, 2, 3, 5, 8, 13)
    print("Same numbers: " + primeNumbers.intersect(fibonacciNumbers))
}
```

Applying the quick-fix replaces the `var` keyword with `val`:

```kotlin
fun example() {
    val primeNumbers = listOf(1, 2, 3, 5, 7, 11, 13)
    val fibonacciNumbers = listOf(1, 1, 2, 3, 5, 8, 13)
    print("Same numbers: " + primeNumbers.intersect(fibonacciNumbers))
}
```

</details>

## CascadeIf

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Cascade 'if' can be replaced with 'when'</summary>

Reports `if` statements with three or more branches that can be simplified into a `when` expression.
**Example:**

```kotlin
fun checkIdentifier(id: String) {
    fun Char.isIdentifierStart() = this in 'A'..'z'
    fun Char.isIdentifierPart() = isIdentifierStart() || this in '0'..'9'

    if (id.isEmpty()) {
        print("Identifier is empty")
    } else if (!id.first().isIdentifierStart()) {
        print("Identifier should start with a letter")
    } else if (!id.subSequence(1, id.length).all(Char::isIdentifierPart)) {
        print("Identifier should contain only letters and numbers")
    }
}
```

Applying the quick-fix converts the `if` cascade to a `when` expression:

```kotlin
fun checkIdentifier(id: String) {
    fun Char.isIdentifierStart() = this in 'A'..'z'
    fun Char.isIdentifierPart() = isIdentifierStart() || this in '0'..'9'

    when {
        id.isEmpty() -> {
            print("Identifier is empty")
        }
        !id.first().isIdentifierStart() -> {
            print("Identifier should start with a letter")
        }
        !id.subSequence(1, id.length).all(Char::isIdentifierPart) -> {
            print("Identifier should contain only letters and numbers")
        }
    }
}
```

</details>

## ClassName

**Severity**: Error </br>
**Highlighting**: Error </br>

<details>
<summary>Class naming convention</summary>

Reports class names that do not follow recommended naming conventions.

Consistent naming allows for easier code reading and understanding.
According to the [official Kotlin Style Guide](https://kotlinlang.org/docs/coding-conventions.html#naming-rules),
class names should start with an uppercase letter and use CamelCase.

It is possible to introduce other naming rules by changing the "Pattern" regular expression.

**Example:**

```kotlin
class user(val name: String)
```

Applying the quick-fix renames the class according to the Kotlin naming conventions:

```kotlin
class User(val name: String)
```

</details>

## ControlFlowWithEmptyBody

**Severity**: Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Control flow with empty body</summary>

Reports `if`, `while`, `do`, or `for` statements with empty bodies.
While occasionally intentional, this construction is confusing and often results from a typo.

Applying the quick-fix removes the redundant statement.

**Example:**

```kotlin
if (a > b) {}
```

</details>

## ConvertLambdaToReference

**Severity**: Information </br>
**Highlighting**: None </br>

<details>
<summary>Can be replaced with function reference</summary>

Reports function literal expressions that can be replaced with a function reference.
Replacing lambdas with function references often makes code more concise and understandable.

**Example:**

```kotlin
fun Int.isEven() = this % 2 == 0

fun example() {
    val numbers = listOf(1, 2, 4, 7, 9, 10)
    val evenNumbers = numbers.filter { it.isEven() }
}
```

After applying the quick-fix:

```kotlin
fun Int.isEven() = this % 2 == 0

fun example() {
    val numbers = listOf(1, 2, 4, 7, 9, 10)
    val evenNumbers = numbers.filter(Int::isEven)
}
```

</details>

## ConvertPairConstructorToToFunction

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Convert Pair constructor to 'to' function</summary>

Reports `Pair` constructor invocations that can be replaced with the `to()` infix function.

Explicit constructor invocations may add verbosity, especially if they are used multiple times.
Replacing constructor calls with `to()` makes code easier to read and maintain.

**Example:**

```kotlin
val countries = mapOf(
    Pair("France", "Paris"),
    Pair("Spain", "Madrid"),
    Pair("Germany", "Berlin")
)
```

After applying the quick-fix:

```kotlin
val countries = mapOf(
    "France" to "Paris",
    "Spain" to "Madrid",
    "Germany" to "Berlin"
)
```

</details>

## ConvertReferenceToLambda

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Can be replaced with lambda</summary>

Reports function reference expressions that can be replaced with a function literal (lambda).

Sometimes, passing a lambda may be more straightforward and more consistent with the rest of the code,
especially when complex logic is added later.

**Example:**

```kotlin
fun Int.isEven() = this % 2 == 0

fun example() {
    val numbers = listOf(1, 2, 4, 7, 9, 10)
    val evenNumbers = numbers.filter(Int::isEven)
}
```

After applying the quick-fix:

```kotlin
fun Int.isEven() = this % 2 == 0

fun example() {
    val numbers = listOf(1, 2, 4, 7, 9, 10)
    val evenNumbers = numbers.filter { it.isEven() }
}
```

</details>

## ConvertTwoComparisonsToRangeCheck

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Two comparisons should be converted to a range check</summary>

Reports two consecutive comparisons that can be converted into a range check.
Checking against a range simplifies code by removing test subject duplication.

**Example:**

```kotlin
fun checkMonth(month: Int): Boolean {
    return month >= 1 && month <= 12
}
```

Applying the quick-fix replaces the comparison with a range check:

```kotlin
fun checkMonth(month: Int): Boolean {
    return month in 1..12
}
```

</details>

## Destructure

**Severity**: Weak Warning </br>
**Highlighting**: Weak Warning </br>

<details>
<summary>Use destructuring declaration</summary>

Reports declarations that can be destructured.
**Example:**

```kotlin
data class My(val first: String, val second: Int, val third: Boolean)

fun foo(list: List<My>) {
    list.forEach { my ->
        println(my.second)
        println(my.third)
    }
}
```

Applying the quick-fix destructures the declaration and introduces new variables named after the corresponding class:

```kotlin
data class My(val first: String, val second: Int, val third: Boolean)

fun foo(list: List<My>) {
    list.forEach { (_, second, third) ->
        println(second)
        println(third)
    }
}
```

</details>

## ExplicitThis

**Severity**: Error </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Redundant explicit 'this'</summary>

Reports explicit usages of `this` where the qualification is redundant and can be omitted.
**Example:**

```kotlin
class C {
    private val i = 1
    fun f() = this.i
}
```

Applying the quick-fix removes the redundant `this`:

```kotlin
class C {
    private val i = 1
    fun f() = i
}
```

</details>

## ForEachParameterNotUsed

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Iterated elements are not used in forEach</summary>

Reports `forEach` loops that do not use iterable values.
**Example:**

```kotlin
listOf(1, 2, 3).forEach { }
```

Applying the quick-fix introduces an anonymous parameter in the `forEach` section:

```kotlin
listOf(1, 2, 3).forEach { _ -> }
```

</details>

## FunctionName

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Function naming convention</summary>

Reports function names that do not follow recommended naming conventions.
**Example:**

```kotlin
fun Foo() {}
```

To fix the problem, rename the function to match the recommended naming conventions.

</details>

## HasPlatformType

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Function or property has platform type</summary>

Reports functions and properties that have a platform type.
To prevent unexpected errors, the type should be declared explicitly.

**Example:**

```kotlin
fun foo() = java.lang.String.valueOf(1)
```

Applying the quick-fix allows you to specify the return type:

```kotlin
fun foo(): String = java.lang.String.valueOf(1)
```

</details>

## ImplicitThis

**Severity**: Weak Warning </br>
**Highlighting**: Weak Warning </br>

<details>
<summary>Implicit 'this'</summary>

Reports implicit references to **this**.
**Example:**

```kotlin
class Foo {
    fun s() = ""

    fun test() {
        s()
    }
}
```

Applying the quick-fix specifies **this** explicitly:

```kotlin
class Foo {
    fun s() = ""

    fun test() {
        this.s()
    }
}
```

</details>

## IncompleteDestructuring

**Severity**: Error </br>
**Highlighting**: Error </br>

<details>
<summary>Incomplete destructuring declaration</summary>

Reports incomplete destructuring declarations.
**Example:**

```kotlin
data class Person(val name: String, val age: Int)
val person = Person("", 0)
val (name) = person
```

Applying the quick-fix completes the destructuring declaration with new variables:

```kotlin
data class Person(val name: String, val age: Int)
val person = Person("", 0)
val (name, age) = person
```

</details>

## IntroduceWhenSubject

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>'when' that can be simplified by introducing an argument</summary>

Reports `when` expressions that can be simplified by introducing a subject argument.
**Example:**

```kotlin
fun test(obj: Any): String {
    return when {
        obj is String -> "string"
        obj is Int -> "int"
        else -> "unknown"
    }
}
```

Applying the quick-fix introduces a subject argument:

```kotlin
fun test(obj: Any): String {
    return when (obj) {
        is String -> "string"
        is Int -> "int"
        else -> "unknown"
    }
}
```

</details>

## JoinDeclarationAndAssignment

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Join declaration and assignment</summary>

Reports property declarations that can be joined with the following assignment.
**Example:**

```kotlin
val x: String
x = System.getProperty("")
```

Applying the quick fix joins the declaration with the assignment:

```kotlin
val x = System.getProperty("")
```

Configure the inspection:

You can disable **Report with complex initialization of member properties** to skip properties with complex initialization. This covers two cases:

1. The property initializer is complex (it is a multiline or a compound/control-flow expression).
2. The property is first initialized and then immediately referenced in subsequent code (for example, to call additional initialization methods).

</details>

## KDocUnresolvedReference

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Unresolved reference in KDoc</summary>

Reports unresolved references in KDoc comments.
**Example:**

```kotlin
/**
 * [unresolvedLink]
 */
fun foo() {}
```

To fix the problem, make the link valid.

</details>

## KotlinConstantConditions

**Severity**: Error </br>
**Highlighting**: Error </br>

<details>
<summary>Constant conditions</summary>

Reports non-trivial conditions and values that statically evaluate to `true`, `false`, `null`, or `zero`.
While sometimes intentional, this often indicates a logical error in the program. Additionally,
it reports unreachable `when` branches and expressions that are statically guaranteed to fail.
Examples:

```kotlin
fun process(x: Int?) {
  val isNull = x == null
  if (!isNull) {
    if (x != null) {} // condition is always true
    require(x!! < 0 && x > 10) // condition is always false
  } else {
    println(x!!) // !! operator will always fail
  }
}
fun process(v: Any) {
  when(v) {
    is CharSequence -> println(v as Int) // cast will always fail
    is String -> println(v) // branch is unreachable
  }
}
```

Uncheck "Warn when constant is stored in variable" to avoid reporting variables with constant values outside of conditions.

New in 2021.3

</details>

## KotlinDeprecation

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Usage of redundant or deprecated syntax or deprecated symbols</summary>

Reports obsolete language features and unnecessarily verbose code constructs during the code cleanup operation (**Code | Code Cleanup**).

Applying the quick-fix automatically updates obsolete language features or unnecessarily verbose code constructs.

It also replaces deprecated symbols with recommended alternatives.

</details>

## KotlinEqualsBetweenInconvertibleTypes

**Severity**: Error </br>
**Highlighting**: Error </br>

<details>
<summary>'equals()' between objects of inconvertible types</summary>

Reports calls to `equals()` where receiver and argument are
of incompatible primitive, enum, or string types.

While such a call might theoretically be useful, most likely it represents a bug.

**Example:**

```kotlin
5.equals("");
```

</details>

## KotlinUnusedImport

**Severity**: Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Unused import directive</summary>

Reports redundant `import` statements.

Default and unused imports can be safely removed.

**Example:**

```kotlin
import kotlin.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*

// jvm specific
import java.lang.*
import kotlin.jvm.*

// js specific
import kotlin.js.*

import java.io.* // this import is unused and could be removed
import java.util.*

fun foo(list: ArrayList<String>) {
    list.add("")
}
```

</details>

## LiftReturnOrAssignment

**Severity**: Error </br>
**Highlighting**: Error </br>

<details>
<summary>Return or assignment can be lifted out</summary>

Reports `if`, `when`, and `try` statements that can be converted to expressions
by lifting the `return` statement or assignments out.
**Example:**

```kotlin
fun foo(arg: Int): String {
    when (arg) {
        0 -> return "Zero"
        1 -> return "One"
        else -> return "Multiple"
    }
}
```

After applying the quick-fix:

```kotlin
fun foo(arg: Int): String {
    return when (arg) {
        0 -> "Zero"
        1 -> "One"
        else -> "Multiple"
    }
}
```

If you would like this inspection to highlight more complex code with multi-statement branches, uncheck the option "Report only if each branch is a single statement".

</details>

## LocalVariableName

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Local variable naming convention</summary>

Reports local variables that do not follow the recommended naming conventions.
You can specify the required pattern in the inspection options.

[Recommended naming conventions](https://kotlinlang.org/docs/coding-conventions.html#function-names): it has to start with a lowercase letter, use CamelCase and no underscores.

**Example:**

```kotlin
fun fibonacciNumber(index: Int): Long = when(index) {
    0 -> 0
    else -> {
        // does not follow naming conventions: contains underscore symbol (`_`)
        var number_one: Long = 0
        // does not follow naming conventions: starts with an uppercase letter
        var NUMBER_TWO: Long = 1
        // follows naming conventions: starts with a lowercase letter, uses camel case and no underscores.
        var numberThree: Long = number_one + NUMBER_TWO

        for(currentIndex in 2..index) {
            numberThree = number_one + NUMBER_TWO
            number_one = NUMBER_TWO
            NUMBER_TWO = numberThree
        }
        numberThree
    }
}
```

</details>

## LoopToCallChain

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Loop can be replaced with stdlib operations</summary>

Reports `for` loops that can be replaced with a sequence of stdlib operations (`map`, `filter`, etc.).
**Example:**

```kotlin
fun foo(list: List<String>): List<Int> {
  val result = ArrayList<Int>()
  for (s in list) {
     if (s.length > 0)
       result.add(s.hashCode())
     }
  return result
}
```

After applying the quick-fix:

```kotlin
fun foo(list: List<String>): List<Int> {
  val result = list
    .filter { it.length > 0 }
    .map { it.hashCode() }
  return result
}
```

</details>

## MayBeConstant

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Might be 'const'</summary>

Reports top-level `val` properties in objects that can be declared as `const`
for better performance and Java interoperability.
**Example:**

```kotlin
object A {
    val foo = 1
}
```

After applying the quick-fix:

```kotlin
object A {
    const val foo = 1
}
```

</details>

## MemberVisibilityCanBePrivate

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Class member can have 'private' visibility</summary>

Reports declarations that can be made `private` to follow the encapsulation principle.
**Example:**

```kotlin
class Service(val url: String) {
    fun connect(): URLConnection = URL(url).openConnection()
}
```

After applying the quick-fix (considering there are no usages of `url` outside of the `Service` class):

```kotlin
class Service(private val url: String) {
    fun connect(): URLConnection = URL(url).openConnection()
}
```

</details>

## MoveVariableDeclarationIntoWhen

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Variable declaration could be moved inside 'when'</summary>

Reports variable declarations that can be moved inside a `when` expression.
**Example:**

```kotlin
fun someCalc(x: Int) = x * 42

fun foo(x: Int): Int {
  val a = someCalc(x)
  return when (a) {
    1 -> a
    2 -> 2 * a
    else -> 24
  }
}
```

After applying the quick-fix:

```kotlin
fun foo(x: Int): Int {
  return when (val a = someCalc(x)) {
    1 -> a
    2 -> 2 * a
    else -> 24
  }
}
```

</details>

## NestedLambdaShadowedImplicitParameter

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Nested lambda has shadowed implicit parameter</summary>

Reports nested lambdas with shadowed implicit parameters.
**Example:**

```kotlin
fun foo(listOfLists: List<List<String>>) {
  listOfLists.forEach {
    it.forEach {
      println(it)
    }
  }
}
```

After applying the quick-fix:

```kotlin
fun foo(listOfLists: List<List<String>>) {
  listOfLists.forEach {
    it.forEach { it1 ->
      println(it1)
    }
  }
}
```

</details>

## PrivatePropertyName

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Private property naming convention</summary>

Reports private property names that do not follow the recommended naming conventions.

Consistent naming allows for easier code reading and understanding.
According to the [official Kotlin Style Guide](https://kotlinlang.org/docs/coding-conventions.html#naming-rules),
private property names should start with a lowercase letter and use camel case.
Optionally, underscore prefix is allowed but only for **private** properties.

It is possible to introduce other naming rules by changing the "Pattern" regular expression.

**Example:**

```kotlin
val _My_Cool_Property = ""
```

Applying the quick-fix renames the class according to the Kotlin naming conventions:

```kotlin
val _myCoolProperty = ""
```

</details>

## PublicApiImplicitType

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Public API declaration with implicit return type</summary>

Reports `public` and `protected` functions and properties that have an implicit return type.
Explicit types are required for API stability.

**Example:**

```kotlin
fun publicFunctionWhichAbusesTypeInference() =
    otherFunctionWithNotObviousReturnType() ?: yetAnotherFunctionWithNotObviousReturnType()
```

After applying the quick-fix:

```kotlin
fun publicFunctionWhichAbusesTypeInference(): **Api** =
    otherFunctionWithNotObviousReturnType() ?: yetAnotherFunctionWithNotObviousReturnType()
```

</details>

## RedundantElseInIf

**Severity**: Error </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Redundant 'else' in 'if'</summary>

Reports redundant `else` in `if` with `return`.

**Example:**

```kotlin
fun foo(arg: Boolean): Int {
    if (arg) return 0
    else { // This else is redundant, code in braces could be just shifted left
        someCode()
    }
}
```

After applying the quick-fix:

```kotlin
fun foo(arg: Boolean): Int {
    if (arg) return 0
    someCode()
}
```

</details>

## RedundantExplicitType

**Severity**: Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Obvious explicit type</summary>

Reports local variable explicit type declarations that are obvious and thus redundant, like `val f: Foo = Foo()`.

**Example:**

```kotlin
class Point(val x: Int, val y: Int)

fun foo() {
    val t: **Boolean** = true
    val p: **Point** = Point(1, 2)
    val i: **Int** = 42
}
```

After applying the quick-fix:

```kotlin
class Point(val x: Int, val y: Int)

fun foo() {
    val t = true
    val p = Point(1, 2)
    val i = 42
}
```

</details>

## RedundantIf

**Severity**: Weak Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Redundant 'if' statement</summary>

Reports `if` statements that can be simplified to a single statement.

**Example:**

```kotlin
fun test(): Boolean {
    if (foo()) {
       return true
    } else {
       return false
    }
}
```

After applying the quick-fix:

```kotlin
fun test(): Boolean {
    return foo()
}
```

</details>

## RedundantNullableReturnType

**Severity**: Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Redundant nullable return type</summary>

Reports functions and variables with nullable return types that never return or become `null`.
**Example:**

```kotlin
fun greeting(user: String): String? = "Hello, $user!"
```

After applying the quick-fix:

```kotlin
fun greeting(user: String): String = "Hello, $user!"
```

</details>

## RedundantSemicolon

**Severity**: Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Redundant semicolon</summary>

Reports redundant semicolons (`;`) that can be safely removed.

Kotlin does not require a semicolon at the end of each statement or expression.
The quick-fix removes redundant semicolons.

**Example:**

```kotlin
val myMap = mapOf("one" to 1, "two" to 2);
myMap.forEach { (key, value) ->  print("$key -> $value")};
```

After applying the quick-fix:

```kotlin
val myMap = mapOf("one" to 1, "two" to 2)
myMap.forEach { (key, value) ->  print("$key -> $value")}
```

There are two cases though where a semicolon is required:

1. Several statements placed on a single line need to be separated with semicolons:

```kotlin
map.forEach { val (key, value) = it; println("$key -> $value") }
```

2. `enum` classes that also declare properties or functions require a semicolon after the list of `enum` constants:

```kotlin
enum class Mode {
    SILENT, VERBOSE;

    fun isSilent(): Boolean = this == SILENT
}
```

</details>

## RedundantVisibilityModifier

**Severity**: Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Redundant visibility modifier</summary>

Reports visibility modifiers that match the default visibility
(`public` for most elements, `protected` for members that override a protected member).

</details>

## RemoveCurlyBracesFromTemplate

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Redundant curly braces in string template</summary>

Reports curly braces in string templates wrapping simple identifiers.
Use the 'Remove curly braces' quick-fix to remove the redundant braces.

**Examples:**

```kotlin
fun redundant() {
   val x = 4
   val y = "${x}" // <== redundant
}

fun correctUsage() {
    val x = "x"
    val y = "${x.length}" // <== Ok
}
```

After applying the quick-fix:

```kotlin
fun redundant() {
   val x = 4
   val y = "$x"
}

fun correctUsage() {
    val x = "x" <== Updated
    val y = "${x.length}"
}
```

</details>

## RemoveEmptyParenthesesFromLambdaCall

**Severity**: Weak Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Unnecessary parentheses in function call with lambda</summary>

Reports redundant empty parentheses in function calls where the sole parameter is a lambda that's outside the parentheses.
Use the 'Remove unnecessary parentheses from function call with lambda' quick-fix to clean up the code.

**Examples:**

```kotlin
fun foo() {
    listOf(1).forEach() {  }
}
```

After applying the quick-fix:

```kotlin
fun foo() {
    listOf(1).forEach {  }
}
```

</details>

## RemoveEmptyPrimaryConstructor

**Severity**: Weak Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Redundant empty primary constructor</summary>

Reports empty primary constructors when they are implicitly available anyway.

A primary constructor is redundant and can be safely omitted when it does not have any annotations or visibility modifiers.
Use the 'Remove empty primary constructor' quick-fix to clean up the code.

**Examples:**

```kotlin
class MyClassA constructor() //  redundant, can be replaced with 'class MyClassA'

annotation class MyAnnotation
class MyClassB @MyAnnotation constructor() //  required because of annotation

class MyClassC private constructor() // required because of visibility modifier
```

</details>

## RemoveExplicitTypeArguments

**Severity**: Weak Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Unnecessary type argument</summary>

Reports function calls with type arguments that can be automatically inferred. Such type arguments are redundant and can be safely omitted.
Use the 'Remove explicit type arguments' quick-fix to clean up the code.

**Examples:**

```kotlin
// 'String' type can be inferred here
fun foo(): MutableList<String> = mutableListOf<String>()

// Here 'String' cannot be inferred, type argument is required.
fun bar() = mutableListOf<String>()
```

After applying the quick-fix:

```kotlin
fun foo(): MutableList<String> = mutableListOf() <== Updated

fun bar() = mutableListOf<String>()
```

</details>

## RemoveForLoopIndices

**Severity**: Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Unused loop index</summary>

Reports `for` loops iterating over a collection using the `withIndex()` function and not using the index variable.
Use the "Remove indices in 'for' loop" quick-fix to clean up the code.

**Examples:**

```kotlin
fun foo(bar: List<String>) {
   for ((index : Int, value: String) in bar.withIndex()) { // <== 'index' is unused
       println(value)
   }
}
```

After applying the quick-fix:

```kotlin
fun foo(bar: List<String>) {
    for (value: String in bar) { // <== '.withIndex()' and 'index' are removed
        println(value)
    }
}
```

</details>

## RemoveRedundantCallsOfConversionMethods

**Severity**: Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Redundant call of conversion method</summary>

Reports redundant calls to conversion methods (e.g., calling `toString()` on a `String` instance or `toDouble()`
on a `Double`).
Use the 'Remove redundant calls of the conversion method' quick-fix to clean up the code.

</details>

## RemoveRedundantQualifierName

**Severity**: Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Redundant qualifier name</summary>

Reports redundant qualifiers (or their parts) on class names, functions, and properties.

A fully qualified name is an unambiguous identifier that specifies which object, function, or property a call refers to.
In the contexts where the name can be shortened, the inspection informs on the opportunity, and the associated
'Remove redundant qualifier name' quick-fix allows amending the code.

**Examples:**

```kotlin
package my.simple.name
import kotlin.Int.Companion.MAX_VALUE

class Foo

fun main() {
    val a = my.simple.name.Foo()    // 'Foo' resides in the declared 'my.simple.name' package, qualifier is redundant
    val b = kotlin.Int.MAX_VALUE    // Can be replaced with 'MAX_VALUE', since it's imported
    val c = kotlin.Double.MAX_VALUE // Can be replaced with 'Double.MAX_VALUE', since built-in types are imported automatically
}
```

After applying the quick-fix:

```kotlin
package my.simple.name
import kotlin.Int.Companion.MAX_VALUE

class Foo

fun main() {
    val a = Foo()
    val b = MAX_VALUE
    val c = Double.MAX_VALUE
}
```

</details>

## RemoveSingleExpressionStringTemplate

**Severity**: Error </br>
**Highlighting**: Error </br>

<details>
<summary>Redundant string template</summary>

Reports single-expression string templates that can be safely removed.
**Example:**

```kotlin
val x = "Hello"
val y = "$x"
```

After applying the quick-fix:

```kotlin
val x = "Hello"
val y = x // <== Updated
```

</details>

## RemoveToStringInStringTemplate

**Severity**: Weak Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Redundant call to 'toString()' in string template</summary>

Reports calls to `toString()` in string templates that can be safely removed.
**Example:**

```kotlin
fun foo(a: Int, b: Int) = a + b

fun test(): String {
    return "Foo: ${foo(0, 4).toString()}" // 'toString()' is redundant
}
```

After applying the quick-fix:

```kotlin
fun foo(a: Int, b: Int) = a + b

fun test(): String {
    return "Foo: ${foo(0, 4)}"
}
```

</details>

## ReplaceCallWithBinaryOperator

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Can be replaced with binary operator</summary>

Reports function calls that can be replaced with binary operators, in particular comparison-related ones.
**Example:**

```kotlin
fun test(): Boolean {
    return 2.compareTo(1) > 0 // replaceable 'compareTo()'
}
```

After applying the quick-fix:

```kotlin
fun test(): Boolean {
    return 2 > 1
}
```

</details>

## ReplaceCollectionCountWithSize

**Severity**: Weak Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Collection count can be converted to size</summary>

Reports calls to `Collection<T>.count()`.

This function call can be replaced with `.size`.

`.size` ensures that the operation is O(1) and won't allocate extra objects, whereas
`count()` could be confused with `Iterable<T>.count()`, which is O(n) and allocating.

**Example:**

```kotlin
fun foo() {
    var list = listOf(1,2,3)
    list.count() // replaceable 'count()'
}
```

After applying the quick-fix:

```kotlin
fun foo() {
    var list = listOf(1,2,3)
    list.size
}
```

</details>

## ReplaceGetOrSet

**Severity**: Weak Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Explicit 'get' or 'set' call</summary>

Reports explicit calls to `get` or `set` functions that can be replaced by an indexing operator `[]`.

Kotlin supports custom implementations for a predefined set of operators on your types.
To overload an operator, mark the corresponding function with the `operator` modifier:

```kotlin
operator fun get(index: Int) {}
operator fun set(index: Int, value: Int) {}
```

The functions above correspond to the indexing operator.

**Example:**

```kotlin
class Test {
    operator fun get(i: Int): Int = 0
}

fun test() {
    Test().get(0) // replaceable 'get()'
}
```

After applying the quick-fix:

```kotlin
class Test {
    operator fun get(i: Int): Int = 0
}

fun test() {
    Test()[0]
}
```

</details>

## ReplaceGuardClauseWithFunctionCall

**Severity**: Error </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Guard clause can be replaced with Kotlin's function call</summary>

Reports guard clauses that can be replaced with a function call.
**Example:**

```kotlin
fun test(foo: Int?) {
    if (foo == null) throw IllegalArgumentException("foo") // replaceable clause
}
```

After applying the quick-fix:

```kotlin
fun test(foo: Int?) {
    checkNotNull(foo)
}
```

</details>

## ReplaceManualRangeWithIndicesCalls

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Range can be converted to indices or iteration</summary>

Reports `until` and `rangeTo` operators that can be replaced with `Collection.indices` or direct collection iteration inside a `for` loop.
Using syntactic sugar makes your code cleaner.

The quick-fix replaces the manual range with the corresponding construct.

**Example:**

```kotlin
fun main(args: Array<String>) {
    for (index in 0..args.size - 1) {
        println(args[index])
    }
}
```

After applying the quick-fix:

```kotlin
fun main(args: Array<String>) {
    for (element in args) {
        println(element)
    }
}
```

</details>

## ReplaceRangeToWithUntil

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>'rangeTo' or the '..' call should be replaced with 'until'</summary>

Reports calls to `rangeTo` or the `..` operator instead of using `until`.
Using corresponding functions makes your code simpler.

The quick-fix replaces `rangeTo` or the `..` call with `until`.

**Example:**

```kotlin
fun foo(a: Int) {
    for (i in 0..a - 1) {

    }
}
```

After applying the quick-fix:

```kotlin
fun foo(a: Int) {
    for (i in 0 until a) {

    }
}
```

</details>

## ReplaceReadLineWithReadln

**Severity**: Weak Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>'readLine' can be replaced with 'readln' or 'readlnOrNull'</summary>

Reports calls to `readLine()` that can be replaced with `readln()` or `readlnOrNull()`.

Using corresponding functions makes your code simpler.

The quick-fix replaces `readLine()!!` with `readln()` and `readLine()` with `readlnOrNull()`.

**Examples:**

```kotlin
val x = readLine()!!
val y = readLine()?.length
```

After applying the quick-fix:

```kotlin
val x = readln()
val y = readlnOrNull()?.length
```

</details>

## ReplaceSizeCheckWithIsNotEmpty

**Severity**: Error </br>
**Highlighting**: Error </br>

<details>
<summary>Size check can be replaced with 'isNotEmpty()'</summary>

Reports `Collection/Array/String` size checks that can be replaced with `isNotEmpty()`.
Using `isNotEmpty()` makes your code simpler.

The quick-fix replaces the size check with `isNotEmpty()`.

**Example:**

```kotlin
fun foo() {
    val arrayOf = arrayOf(1, 2, 3)
    arrayOf.size > 0
}
```

After applying the quick-fix:

```kotlin
fun foo() {
    val arrayOf = arrayOf(1, 2, 3)
    arrayOf.isNotEmpty()
}
```

</details>

## ReplaceSizeZeroCheckWithIsEmpty

**Severity**: Error </br>
**Highlighting**: Error </br>

<details>
<summary>Size zero check can be replaced with 'isEmpty()'</summary>

Reports `size == 0` checks on `Collections/Arrays/Strings` that can be replaced with `isEmpty()`.
Using `isEmpty()` makes your code simpler.

The quick-fix replaces the size check with `isEmpty()`.

**Example:**

```kotlin
fun foo() {
    val arrayOf = arrayOf(1, 2, 3)
    arrayOf.size == 0
}
```

After applying the quick-fix:

```kotlin
fun foo() {
    val arrayOf = arrayOf(1, 2, 3)
    arrayOf.isEmpty()
}
```

</details>

## ReplaceSubstringWithTake

**Severity**: Error </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>'substring' call should be replaced with 'take' call</summary>

Reports calls like `s.substring(0, x)` that can be replaced with `s.take(x)`.
Using `take()` makes your code simpler.

The quick-fix replaces the `substring` call with `take()`.

**Example:**

```kotlin
fun foo(s: String) {
    s.substring(0, 10)
}
```

After applying the quick-fix:

```kotlin
fun foo(s: String) {
    s.take(10)
}
```

</details>

## ReplaceToStringWithStringTemplate

**Severity**: Information </br>
**Highlighting**: None </br>

<details>
<summary>Call of 'toString' could be replaced with string template</summary>

Reports `toString` function calls that can be replaced with a string template.
Using string templates makes your code simpler.

The quick-fix replaces `toString` with a string template.

**Example:**

```kotlin
fun test(): String {
    val x = 1
    return x.toString()
}
```

After applying the quick-fix:

```kotlin
fun test(): String {
    val x = 1
    return "$x"
}
```

</details>

## ReplaceWithOperatorAssignment

**Severity**: Error </br>
**Highlighting**: Error </br>

<details>
<summary>Assignment can be replaced with operator assignment</summary>

Reports variable re-assignments (such as `y = y + x`) that can be replaced with an operator assignment.
The quick-fix replaces the assignment with an assignment operator.

**Example:**

```kotlin
fun foo() {
    val list = mutableListOf(1, 2, 3)
    list = list + 4
}
```

After applying the quick-fix:

```kotlin
fun foo() {
    val list = mutableListOf(1, 2, 3)
    list += 4
}
```

</details>

## ScopeFunctionConversion

**Severity**: Information </br>
**Highlighting**: None </br>

<details>
<summary>Scope function can be converted to another one</summary>

Reports scope functions (`let`, `run`, `apply`, `also`) that can be converted into one another.
Choosing the most appropriate scope function makes your code cleaner.

The quick-fix replaces the scope function with another one.

**Example:**

```kotlin
val x = "".let {
    it.length
}
```

After applying the quick-fix:

```kotlin
val x = "".run {
    length
}
```

</details>

## SelfAssignment

**Severity**: Error </br>
**Highlighting**: Error </br>

<details>
<summary>Redundant assignment</summary>

Reports assignments of a variable to itself.
The quick-fix removes the redundant assignment.

**Example:**

```kotlin
fun test() {
    var bar = 1
    bar = bar
}
```

After applying the quick-fix:

```kotlin
fun test() {
    var bar = 1
}
```

</details>

## SimplifiableCallChain

**Severity**: Weak Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Call chain on collection type can be simplified</summary>

Reports two-call chains replaceable by a single call.
It can help you avoid redundant code execution.

The quick-fix replaces the call chain with a single call.

**Example:**

```kotlin
fun main() {
    listOf(1, 2, 3).filter { it > 1 }.count()
}
```

After applying the quick-fix:

```kotlin
fun main() {
    listOf(1, 2, 3).count { it > 1 }
}
```

</details>

## SimplifyBooleanWithConstants

**Severity**: Error </br>
**Highlighting**: Error </br>

<details>
<summary>Boolean expression can be simplified</summary>

Reports boolean expression parts that can be reduced to constants.
The quick-fix simplifies the condition.

**Example:**

```kotlin
fun use(arg: Boolean) {
    if (false == arg) {

    }
}
```

After applying the quick-fix:

```kotlin
fun use(arg: Boolean) {
    if (!arg) {

    }
}
```

</details>

## TrailingComma

**Severity**: Error </br>
**Highlighting**: Error </br>

<details>
<summary>Trailing comma recommendations</summary>

Reports trailing commas that do not follow the recommended [style guide](https://kotlinlang.org/docs/coding-conventions.html#trailing-commas).

</details>

## UnlabeledReturnInsideLambda

**Severity**: Error </br>
**Highlighting**: Error </br>

<details>
<summary>Unlabeled return inside lambda</summary>

Reports unlabeled `return` expressions inside inline lambdas.
Such expressions can be confusing because it may be unclear which scope the `return` applies to.

The **Change to return@…** quick-fix can be used to amend the code automatically.

Example:

```kotlin
fun test(list: List<Int>) {
    list.forEach {
        // This return expression returns from the function test
        // One can change it to return@forEach to change the scope
        if (it == 10) return
    }
}
```

After applying the quick-fix:

```kotlin
fun test(list: List<Int>) {
    list.forEach {
        if (it == 10) return@test
    }
}
```

</details>

## UnnecessaryVariable

**Severity**: Weak Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Unnecessary local variable</summary>

Reports local variables that are declared only to be immediately returned in the next line or are exact copies of other variables.
Such variables can be safely inlined to make the code more clear.

**Example:**

```kotlin
fun sum(a: Int, b: Int): Int {
    val c = a + b
    return c
}
```

After applying the quick-fix:

```kotlin
fun sum(a: Int, b: Int): Int {
    return a + b
}
```

Configure the inspection:

Use the **Report immediately returned variables** option to flag variables that are returned right after declaration.
This option is disabled by default because descriptive variable names can sometimes enhance readability.

</details>

## UnusedEquals

**Severity**: Error </br>
**Highlighting**: Error </br>

<details>
<summary>Unused equals expression</summary>

Reports unused `equals`(`==`) expressions.

</details>

## UnusedReceiverParameter

**Severity**: Warning </br>
**Highlighting**: Strikethrough </br>

<details>
<summary>Unused receiver parameter</summary>

Reports unused extension function/property receiver parameter instances.
**Remove redundant receiver parameter** can be used to amend the code automatically.

</details>

## UseExpressionBody

**Severity**: Warning </br>
**Highlighting**: Warning </br>

<details>
<summary>Expression body syntax is preferable here</summary>

Reports `return` expressions (such as single-line functions or `when` blocks) that can be replaced with expression body syntax.
Expression body syntax is recommended by the [style guide](https://kotlinlang.org/docs/coding-conventions.html#functions).

The **Convert to expression body** quick-fix can be used to amend the code automatically.

Example:

```kotlin
fun sign(x: Int): Int {
    return when { // <== can be simplified
        x < 0 -> -1
        x > 0 -> 1
        else -> 0
    }
}
```

After applying the quick-fix:

```kotlin
fun sign(x: Int): Int = when {
    x < 0 -> -1
    x > 0 -> 1
    else -> 0
}
```

</details>

## WarningOnMainUnusedParameterMigration

**Severity**: Weak Warning </br>
**Highlighting**: Weak Warning </br>

<details>
<summary>Unused 'args' on 'main' since Kotlin 1.4</summary>

Reports `main` function with a single unused parameter.
Since Kotlin 1.4, a `main` function without parameters can serve as the entry point for a Kotlin program.
The compiler reports a warning when the `main` function retains an unused parameter.

</details>

