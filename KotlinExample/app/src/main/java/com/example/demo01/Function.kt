package com.example.demo01

/**
 * File description.
 *
 * @author dsh
 * @date 2019-08-29
 */
fun main(array: Array<String>) {
    var iterator = array.iterator()
    iterator.forEach { println(it.toString()) }

    println(max(1,100))
    println(max1(1,100))

}

/**
 * 常见的函数
 */
fun max(a:Int,b:Int) : Int{
    return if(a > b) a else b
}

/**
 * 表达式函数
 */
fun max1(a:Int,b:Int) = if(a > b) a else b