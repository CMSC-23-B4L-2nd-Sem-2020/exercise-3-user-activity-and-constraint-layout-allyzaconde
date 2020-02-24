package com.example.exer3_conde_b4l

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button_done).setOnClickListener {
            updateNickname(it)
        }
    }

    fun updateNickname(view: View) {
        val nicknameEditText = findViewById<EditText>(R.id.edit_nickname)
        val nicknameTextView = findViewById<TextView>(R.id.view_nickname)

        nicknameTextView.text = nicknameEditText.text

            nicknameEditText.visibility = View.GONE
            view.visibility = View.GONE
            nicknameTextView.visibility = View.VISIBLE


        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun changeNickname(view: View) {
        val nicknameEditText = findViewById<EditText>(R.id.edit_nickname)
        val nicknameTextView = findViewById<TextView>(R.id.view_nickname)
        val nicknameButton = findViewById<Button>(R.id.button_done)

        nicknameEditText.visibility = View.VISIBLE
        nicknameButton.visibility = View.VISIBLE
        nicknameTextView.visibility = View.GONE
    }

    fun closeLights(view: View){

    }

    fun retry(view: View){

    }


}
