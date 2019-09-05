package com.example.kotlin.base

import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-01
 */
abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {


    final override fun onClick(view: View?) {
        widgetClick(view)
    }

    abstract fun widgetClick(view: View?)

}