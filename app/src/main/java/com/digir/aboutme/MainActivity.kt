package com.digir.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.digir.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var B : ActivityMainBinding

    //Constructor use only first value, not both
    private val myName: MyName = MyName("Kamil Tyszkiewicz")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        B = DataBindingUtil.setContentView(this, R.layout.activity_main)

        B.myName = myName   //set with databinding

//        findViewById<Button>(R.id.done_button).setOnClickListener {
//            //'it' is reference to the done button which is view passed as an argument
//            addNickname(it)
//        }
        B.doneButton.setOnClickListener {
            //'it' is reference to the done button which is view passed as an argument
            addNickname(it)
        }
    }

    private fun addNickname(view: View) {
//        val editText = findViewById<EditText>(R.id.nickname_edit)
//        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)
        B.apply {
            nicknameText.text = nicknameEdit.text
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll() //We must invalidate all binding expressions which get created with correct data
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

//        editText.visibility = View.GONE
//        view.visibility = View.GONE
//        nicknameTextView.visibility = View.VISIBLE

        //Hide the keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }
}