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

class MainActivity : AppCompatActivity() {

    private var moveCount:Int = 0
    private lateinit var moveCountView:TextView
    private val array: IntArray = intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0) //array of the 5x5 grid, 0 as lit up and 1 as lights out
    private var check:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_done).setOnClickListener {
            updateNickname(it) //updates the nickname after clicking the done button
        }
        moveCountView = findViewById(R.id.view_count) //shows on screen the number of clicks the user had

        setListeners() //sets all the listeners on clicking one of the 5x5 lights out array
    }

    fun updateNickname(view: View) { //receives the info inputted by the user and gives it to the view_nickname textview
        val nicknameEditText = findViewById<EditText>(R.id.edit_nickname)
        val nicknameTextView = findViewById<TextView>(R.id.view_nickname)

        nicknameTextView.text = nicknameEditText.text

        nicknameEditText.visibility = View.GONE
        view.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE

        //closes the keyboard after pressing the done button
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun changeNickname(view: View) { //makes the edit text and done button appear to update the nickname
        val nicknameEditText = findViewById<EditText>(R.id.edit_nickname)
        val nicknameTextView = findViewById<TextView>(R.id.view_nickname)
        val nicknameButton = findViewById<Button>(R.id.button_done)

        nicknameEditText.visibility = View.VISIBLE
        nicknameButton.visibility = View.VISIBLE
        nicknameTextView.visibility = View.GONE
    }

    private fun getId(int: Int): Int{ //holds all the ids of each boxes in a list for easy access
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

    private fun setListeners(){ //sets listeners to each box
        for (item in (0..24)){
            findViewById<TextView>(getId(item)).setOnClickListener{
                closeLights(it)
            }
        }
    }

    private fun closeLights(view: View){ //does the function of switching the lights on or off in the code
        if(moveCount == 0){ //checks if the first moves hasn't been done yet, and it will show the move counter
            val show = findViewById<TextView>(R.id.view_count)
            show.visibility = View.VISIBLE
        }
        moveCount++ //adds one move every click of the box
        moveCountView.text = moveCount.toString()

        if(moveCount == 1){ //checks if move or moves is to be used (for grammar purposes)
            moveCountView.append(" Move\n")
        }else{
            moveCountView.append(" Moves\n")
        }

        for(item in (0..24)){ //checks which box the user clicked and it will use the checkLights function (the actual code for turning on or off the lights)
            if(getId(item) == view.id){
                checkLights(item)
            }
        }
        if(check){ //checks if the game has been won and it will reset the game
            retry(view)
            check = false
        }
        check(view) //checks if the game is solved
    }

    private fun checkLights(int:Int){
        if(array[int] == 0){ //for checking the light of the middle box
            array[int] = 1
            findViewById<TextView>(getId(int)).setBackgroundColor(Color.DKGRAY)
        }else{
            array[int] = 0
            findViewById<TextView>(getId(int)).setBackgroundColor(Color.CYAN)
        }

        if(int<24){ //keeps the code from accessing the array greater than 24
            if(array[int+1] == 0 && ((int+1)%5)!=0){ //for checking the light of the right box
                array[int+1] = 1
                findViewById<TextView>(getId(int+1)).setBackgroundColor(Color.DKGRAY)
            }else if(array[int+1] == 1 && ((int+1)%5)!=0){
                array[int+1] = 0
                findViewById<TextView>(getId(int+1)).setBackgroundColor(Color.CYAN)
            }
        }

        if(int>0){ //keeps the code from accessing the array less than 0
            if(array[int-1] == 0 && (int%5)!=0){ //for checking the light of the left box
                array[int-1] = 1
                findViewById<TextView>(getId(int-1)).setBackgroundColor(Color.DKGRAY)
            }else if(array[int-1] == 1 && (int%5)!=0){
                array[int-1] = 0
                findViewById<TextView>(getId(int-1)).setBackgroundColor(Color.CYAN)
            }
        }

        if(int>4){ //keeps the code from accessing the array less than 4
            if(array[int-5] == 0){ //for checking the light of the top box
                array[int-5] = 1
                findViewById<TextView>(getId(int-5)).setBackgroundColor(Color.DKGRAY)
            }else{
                array[int-5] = 0
                findViewById<TextView>(getId(int-5)).setBackgroundColor(Color.CYAN)
            }
        }

        if(int<20){ //keeps the code from accessing the array greater than 20
            if(array[int+5] == 0){ //for checking the light of the lower box
                array[int+5] = 1
                findViewById<TextView>(getId(int+5)).setBackgroundColor(Color.DKGRAY)
            }else{
                array[int+5] = 0
                findViewById<TextView>(getId(int+5)).setBackgroundColor(Color.CYAN)
            }
        }
    }

    fun retry(view: View){ //resets the whole game anew
        moveCount = 0
        val show = findViewById<TextView>(R.id.view_count)
        show.visibility = View.GONE
        for(item in (0..24)){
            findViewById<TextView>(getId(item)).setBackgroundColor(Color.CYAN)
            array[item] = 0
        }
    }

    @SuppressLint("ResourceType")
    private fun check(view: View){ //checks if the games is solved
        var counter = 0
        for(i in (0..24)){
            if(array[i]==1){
                counter++
            }
        }
        if(counter == 25){
            val winner:TextView =findViewById(R.id.view_count)
            winner.setText(R.string.winner)
            winner.append(" ")
            winner.append(moveCount.toString())
            winner.append(" moves :)")
            check = true
        }else{
            return
        }

    }
}
