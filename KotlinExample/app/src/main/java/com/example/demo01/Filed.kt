package com.example.demo01

/**
 * File description.
 *
 * @author dsh
 * @date 2019-08-29
 */
fun main(args: Array<String>) {
    var a :Int = 10
    var b : String
    println(a)

    //字符串模板
    val name = if (args.size > 0 ) args[0] else " world"
    println("hello $name")

    var person: Person = Person("李强")
    println("${person.name}")
    println("${person.age}") //加1
    println("${person.realAge()}") //
    person.addAge()
    println("${person.age}")
    println("${person.realAge()}")
}