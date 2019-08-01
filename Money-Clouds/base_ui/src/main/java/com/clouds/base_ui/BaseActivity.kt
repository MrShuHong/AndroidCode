package com.clouds.base_ui

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View

abstract class BaseActivity : AppCompatActivity() {

    private var mContentView: View? = null
    private var mTitleLayout: View? = null

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        initParams()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContentView = LayoutInflater.from(this).inflate(bindLayout(), null)

        if (mContentView !=null){
            mTitleLayout = mContentView!!.findViewById<View>(R.id.title_layout)
        }

        setContentView(mContentView)
        initParams()
        initView()
        doBusiness(this)
    }

    abstract fun doBusiness(context: Context)

    abstract fun initView()

    abstract fun initParams()

    abstract fun bindLayout(): Int
}

