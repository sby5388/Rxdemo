package com.by5388.rxdemo.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.by5388.rxdemo.R

/**
 * @author  by5388  on 2018/5/9.
 */
class KotlinClassActivity : AppCompatActivity {

    private val textViewId: TextView? = null
    private val textViewName: TextView? = null
    private var button1: Button? = null
    private var button2: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

    }

    private fun initView() {
        button1 = this.findViewById(R.id.button1) as Button?
        button2 = this.findViewById(R.id.button2) as Button?

        button1!!.setOnClickListener {
            Toast.makeText(this, "button1", Toast.LENGTH_SHORT).show()
        }
        button2!!.setOnClickListener {
            Toast.makeText(this, "button2", Toast.LENGTH_SHORT).show()
        }
    }

    constructor() : super()
}