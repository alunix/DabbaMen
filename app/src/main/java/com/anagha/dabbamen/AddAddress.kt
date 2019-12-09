package com.anagha.dabbamen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class AddAddress : AppCompatActivity(), View.OnClickListener {

    lateinit var addnewaddress_button: Button

    var _context: Context = this@AddAddress
    lateinit var mToolbar: Toolbar;
    override fun onClick(v: View?) {
        
        Toast.makeText(
            _context,
            "Add Address Clicked",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_address)
        mToolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(mToolbar);
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        mToolbar.title = "Add Address"
        mToolbar.setNavigationOnClickListener(View.OnClickListener { onBackPressed() })
        initUI()
        uiListener()
    }

    private fun uiListener() {
        addnewaddress_button.setOnClickListener(this)
    }

    @SuppressLint("NewApi")
    private fun initUI() {
        this.addnewaddress_button = findViewById<Button>(R.id.addnewaddress_button)
    }
}