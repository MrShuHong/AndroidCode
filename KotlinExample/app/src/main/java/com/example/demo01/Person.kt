package com.example.demo01

/**
 * File description.
 *
 * @author dsh
 * @date 2019-08-29
 */
class Person(val name: String) {
    var age: Int = 0
        get():Int {
            //return age+1 会报错，原因是调用age 等于是在调用getAge方法， 形成了一个无限嵌套
            return field + 1 // field 就代表当前这个字段
        }
        private set  // 修改方法的访问权限

    //非set get方法统一调用的是属性的get方法，如下面这个age+1  其实是getAge+1
    fun addAge(){
        age += 1  // age = age + 1 // age = getAge + 1 // age = 1+ 1
    }

    fun realAge(): Int{

        return age
    }

    override fun toString(): String {
        return super.toString()
    }

    //对象的 equals
    //kotlin  ==  和 ===
    //== 本质是调用equals来比较  === 是比较两个对象的引用


}