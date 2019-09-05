package com.example.demo01

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-01
 */
val set = hashSetOf(1,2,3,4)
val list = arrayListOf(2,3,4,6)
val map = hashMapOf(1 to "one",2 to "two")

/*
fun <T> joinToString(
    collection:Collection<T>,
    //设置默认参数
    separator:String = ", ",
    prefix:String = "",
    postfix:String = ""
):String{
    val result = StringBuilder(prefix)
    for ((index ,element) in collection.withIndex()){
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}
*/

fun main(args:Array<String>) {
    //kotlin 函数的调用可以这样
    //joinToString(list,separator = "/",prefix = "(",postfix = ")")

    //因为设置了默认参数所以可以这样调用
    //joinToString(list)

    //可变参数展开运算符
    listOf1("args",*args) // *args  展开操作符

    //中缀调用
    val map = mapOf(1 to "one",2 to "two")

    //解构申明
    val (number,name) = 1 to "one"

    parsePath("/user/ding/kotlin/书宏.txt")

}

fun parsePath(path : String){
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    println("Dir $directory ,name $fileName, extension $extension")
}


fun <T>listOf1(vararg values:T): List<Array<out T>> {
    return listOf(values)
}

fun <T> Collection<T>.joinToString(
    separator: String=",",
    prefix: String="",
    postfix: String=""): String{

    val result = StringBuilder(prefix)
    for((index,element) in this.withIndex()){
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)

    return result.toString()

}

