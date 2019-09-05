package com.example.demo01

/**
 * File description.
 *
 * @author dsh
 * @date 2019-08-31
 */
interface Expr
class Num(val value:Int) : Expr
class Sum(val left:Num ,val right: Num) :Expr
class When {

    //when 语句用法
    fun getResult(score:Int):String{
        return when{
            (score < 60) ->  "及格"
            (score < 80) ->  "良好"
            (score < 100) ->  "优秀"
            else -> "错误"
        }
    }

    fun evalIf(e:Expr):Int{
        if(e is Num){
            val n = e as Num
            return n.value
        }
        if (e is Sum){
            return evalIf(e.left) + evalIf(e.right)
        }
        throw IllegalArgumentException("Unknown expression")
    }

    //if表达式
    fun evalIf1(e :Expr):Int =
        if (e is Num){
            e.value
        }else if (e is Sum){
            evalIf1(e.left) + evalIf1(e.right)
        }else{
            throw IllegalArgumentException("Unknown expression")
        }

    fun evalWhen(e:Expr):Int{
        return when(e){
            is Num ->e.value
            is Sum -> evalWhen(e.left)+evalWhen(e.right)
            else -> throw IllegalArgumentException("Unknown expression")
        }
    }
}