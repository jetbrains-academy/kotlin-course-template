package org.jetbrains.academy.kotlin.template

fun invokeSayHello(howManyTimes: Int): String {
    return List(howManyTimes) { sayHello() }.joinToString(System.lineSeparator())
}

fun main() {
    println("How many times should I print Hello?")
    val howManyTimes = readln().toInt()
    println(invokeSayHello(howManyTimes))
}
