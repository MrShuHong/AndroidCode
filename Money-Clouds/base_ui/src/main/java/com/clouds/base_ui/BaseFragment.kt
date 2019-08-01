package com.clouds.base_ui


import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 *
 */
abstract class BaseFragment : Fragment() {


    private var mContentView: View? = null
    private var mTitleLayout: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mContentView = LayoutInflater.from(context).inflate(bindLayout(), null)

        mTitleLayout = mContentView!!.findViewById<View>(R.id.title_layout)

        if(mTitleLayout != null){

        }

        initParams()
        initView()
        doBusiness(context)

        return mContentView
    }

    abstract fun doBusiness(context: Context?)

    abstract fun initView()

    abstract fun initParams()

    abstract fun bindLayout(): Int

}
