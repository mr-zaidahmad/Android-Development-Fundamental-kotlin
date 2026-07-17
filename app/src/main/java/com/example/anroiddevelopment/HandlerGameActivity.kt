package com.example.anroiddevelopment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class HandlerGameActivity : AppCompatActivity() {

    private lateinit var questiontextView:TextView
    private lateinit var  resultTextView: TextView
    private lateinit var timetextview:TextView
    private lateinit var  ScoreTextview:TextView
    private lateinit var  btnCorrect:ImageButton
    private lateinit var  btnInCorrect:ImageButton

    private var isResultCorrect :Boolean=false
    private var seconds :Int=9
    private var score :Int=0
    private var stopTimer:Boolean=false

    fun generateQuestion(){
        isResultCorrect=true
        val randon=Random
        val a=randon.nextInt(100)
        val b=randon.nextInt(100)
        var result=a+b

        var f =randon.nextFloat()
        if (f>0.5f){
            result=randon.nextInt(100)
            isResultCorrect=false
        }
        questiontextView.text="$a + $b"
        resultTextView.text="$result"
    }
    fun verifyanswer(answer :Boolean){
        if (answer ==isResultCorrect){
            score +=1
            ScoreTextview.text= getString(R.string.score, score)
        }
        generateQuestion()
    }

    fun timer(){
        var handler =Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                timetextview.text = getString(R.string.time, seconds)
                seconds--
                if (seconds < 0) {
                    val i = Intent(this@HandlerGameActivity, HandlerScoreActivity::class.java)
                    i.putExtra("score", score)
                    startActivity(i)
                    stopTimer = true
                }
                if (!stopTimer) {
                    handler.postDelayed(this, 1000)
                }
            }
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_handler_game)
        questiontextView=findViewById(R.id.number)
        resultTextView=findViewById(R.id.result)
        timetextview=findViewById(R.id.txtTime)
        ScoreTextview=findViewById(R.id.txtScore)
        btnCorrect=findViewById(R.id.btnCorrect)
        btnInCorrect=findViewById(R.id.btnIncorrect)

        timer()

        generateQuestion()

        btnCorrect.setOnClickListener{
            verifyanswer(true)
        }
        btnInCorrect.setOnClickListener{
            verifyanswer(false)
        }

    }
    override fun onPause() {
        super.onPause()
        stopTimer = true
        finish()


         }
}