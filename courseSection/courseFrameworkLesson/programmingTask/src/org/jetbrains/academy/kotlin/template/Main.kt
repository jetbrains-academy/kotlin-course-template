package org.jetbrains.academy.kotlin.template

// Since the file from the first lesson will be propagated,
// you can put the solution here
fun invokeSayBye(howManyTimes: Int): String {
    return List(howManyTimes) { sayBye() }.joinToString(System.lineSeparator())
}

fun main() {
    println("How many times should I print Bye?")
    val howManyTimes = readln().toInt()
    println(invokeSayBye(howManyTimes))
}
