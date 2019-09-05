package com.example.demo01

/**
 * File description.
 *
 * @author dsh
 * @date 2019-08-31
 */
enum class Color1{
    RED,Green
}

enum class Color(val r:Int,val g:Int,val b:Int){
    RED(255,0,0),ORANGE(255,165,0),
    YELLOW(255,255,0);

    fun rgb() = (r*256+g)*256 + b
}