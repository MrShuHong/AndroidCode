package com.example.kotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import com.example.kotlin.base.BaseActivity

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        var txt_test = findViewById<TextView>(R.id.txt_test)
        txt_test.setOnClickListener(this)
    }


    override fun widgetClick(view: View?) {

    }



}
