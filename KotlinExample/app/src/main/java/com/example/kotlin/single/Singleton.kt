package com.example.kotlin.single

import android.view.View


/**
 * File description.
 *
 * @author dsh
 * @date 2019-08-23
 */
object Singleton {

    val name :String= "jack";
    lateinit var listener : MyClickListener

    interface MyClickListener{
        fun onClick(view : View)
    }
}



