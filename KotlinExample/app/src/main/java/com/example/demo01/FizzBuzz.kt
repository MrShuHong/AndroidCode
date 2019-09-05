package com.example.demo01

import java.util.*

/**
 * File description.
 *
 * @author dsh
 * @date 2019-08-31
 */
fun main() {
    //区间是左右包含的
   /*for (i in 1..30) {
       println(fizzBuzz(i))
   }*/

    /*for(i in 30 downTo 1 step 2){
        println(fizzBuzz(i))
    }*/

    //迭代map
    val binaryReps = TreeMap<Char,String>()

    for(c in 'A'..'F'){
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary
    }

    for((key,value) in binaryReps){
        println("($key , $value)")
    }

    val list = arrayListOf("10","11","1001")
    for((index,element) in list.withIndex()){
        println("$index  $element")
    }
}

fun fizzBuzz(i:Int) = when{
    i%15==0 -> "FizzBuzz"
    i%3 ==0 -> "Fizz"
    i%5 ==0 -> "Buzz"
    else -> "$i"
}

fun isLetter(c:Char) = c in 'a'..'c' || c in 'A'..'C'
fun isNotDigit(c: Char) = c !in '0'..'9'

fun recognize(c :Char) = when(c){
    in '0'..'9' -> "It is a digit"
    in 'a'..'z',in 'A'..'Z' -> "It is a letter"
    else -> "I do not know... "
}

