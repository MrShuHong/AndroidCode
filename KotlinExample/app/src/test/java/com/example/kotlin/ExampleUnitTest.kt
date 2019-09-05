package com.example.kotlin

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testIntArray(){

        var start = System.currentTimeMillis()
        var array = IntArray(100001)
        var sum = 0
        for(i in 1..100000){
            array[i] = i
        }

        array.forEach {
            sum += it
        }

        var result = sum / 100000

        System.out.println("Array 用时：${System.currentTimeMillis() - start}")
        System.out.println("result：${result}")

    }

    
}
