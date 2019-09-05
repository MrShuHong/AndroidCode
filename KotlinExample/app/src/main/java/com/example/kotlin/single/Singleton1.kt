package com.example.kotlin.single

/**
 * File description.
 *
 * @author dsh
 * @date 2019-08-24
 */
class Singleton1 private constructor(){

    var name  = "ssss"

   companion object {
        fun getInstance() = Singleton1()
    }
}