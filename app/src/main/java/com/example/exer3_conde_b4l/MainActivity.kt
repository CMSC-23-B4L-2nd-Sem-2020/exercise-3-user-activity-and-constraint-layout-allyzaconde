package com.example.exer3_conde_b4l

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private var moveCount:Int = 0
    private lateinit var moveCountView:TextView
    private val array: IntArray = intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button_done).setOnClickListener {
            updateNickname(it)
        }
        moveCountView = findViewById(R.id.view_count)
        setListeners()
        check()
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

    private fun getId(int: Int): Int{
        val list: List<Int> = listOf(
            R.id.box_1,
            R.id.box_2,
            R.id.box_3,
            R.id.box_4,
            R.id.box_5,
            R.id.box_6,
            R.id.box_7,
            R.id.box_8,
            R.id.box_9,
            R.id.box_10,
            R.id.box_11,
            R.id.box_12,
            R.id.box_13,
            R.id.box_14,
            R.id.box_15,
            R.id.box_16,
            R.id.box_17,
            R.id.box_18,
            R.id.box_19,
            R.id.box_20,
            R.id.box_21,
            R.id.box_22,
            R.id.box_23,
            R.id.box_24,
            R.id.box_25
        )
        return list[int]
    }

    private fun setListeners(){
        for (item in (0..24)){
            findViewById<TextView>(getId(item)).setOnClickListener{
                closeLights(it)
            }
        }
    }

    private fun closeLights(view: View){
        if(moveCount == 0){
            val show = findViewById<TextView>(R.id.view_count)
            show.visibility = View.VISIBLE
        }
        moveCount++
        moveCountView.text = moveCount.toString()

        for(item in (0..24)){
            if(getId(item) == view.id){
                checkLights(item)
            }
        }
    }

    private fun checkLights(int:Int){
        if(array[int] == 0){
            array[int] = 1
            findViewById<TextView>(getId(int)).setBackgroundColor(Color.DKGRAY)
        }else{
            array[int] = 0
            findViewById<TextView>(getId(int)).setBackgroundColor(Color.CYAN)
        }

        if(int<24){
            if(array[int+1] == 0 && ((int+1)%5)!=0){
                array[int+1] = 1
                findViewById<TextView>(getId(int+1)).setBackgroundColor(Color.DKGRAY)
            }else if(array[int+1] == 1 && ((int+1)%5)!=0){
                array[int+1] = 0
                findViewById<TextView>(getId(int+1)).setBackgroundColor(Color.CYAN)
            }
        }

        if(int>0){
            if(array[int-1] == 0 && (int%5)!=0){
                array[int-1] = 1
                findViewById<TextView>(getId(int-1)).setBackgroundColor(Color.DKGRAY)
            }else if(array[int-1] == 1 && (int%5)!=0){
                array[int-1] = 0
                findViewById<TextView>(getId(int-1)).setBackgroundColor(Color.CYAN)
            }
        }

        if(int>4){
            if(array[int-5] == 0){
                array[int-5] = 1
                findViewById<TextView>(getId(int-5)).setBackgroundColor(Color.DKGRAY)
            }else{
                array[int-5] = 0
                findViewById<TextView>(getId(int-5)).setBackgroundColor(Color.CYAN)
            }
        }

        if(int<20){
            if(array[int+5] == 0){
                array[int+5] = 1
                findViewById<TextView>(getId(int+5)).setBackgroundColor(Color.DKGRAY)
            }else{
                array[int+5] = 0
                findViewById<TextView>(getId(int+5)).setBackgroundColor(Color.CYAN)
            }
        }
    }

    fun retry(view: View){
        moveCount = 0
        val show = findViewById<TextView>(R.id.view_count)
        show.visibility = View.GONE
        for(item in (0..24)){
            findViewById<TextView>(getId(item)).setBackgroundColor(Color.CYAN)
            array[item] = 0
        }
    }

    @SuppressLint("ResourceType")
    private fun check(){
        for(i in (0..24)){
            if(array[i]==0){
                return
            }
        }
        moveCountView = findViewById(R.string.winner)
    }

}
