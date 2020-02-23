package com.example.exer3_conde_b4l

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

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
    }

    fun changeNickname(view: View) {
        val nicknameEditText = findViewById<EditText>(R.id.edit_nickname)
        val nicknameTextView = findViewById<TextView>(R.id.view_nickname)

        nicknameEditText.visibility = View.VISIBLE
        view.visibility = View.VISIBLE
        nicknameTextView.visibility = View.GONE
    }
}
