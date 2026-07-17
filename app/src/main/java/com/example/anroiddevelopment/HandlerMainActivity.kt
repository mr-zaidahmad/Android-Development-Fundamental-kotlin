package com.example.anroiddevelopment

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HandlerMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_handler_main)
        //now in share button we have to put implicit intent with plain text.
        val sharebtn=findViewById<ImageButton>(R.id.sharee)  //here we call sharee from xml to mainactivity
        sharebtn.setOnClickListener {    //use to provide clickable functionality
            val intent=Intent(Intent.ACTION_SEND)    //action send is the type of intent which we use it at the time
            //when we want to send something.like share a link of app to whatsapp.

            intent.type= getString(R.string.text_plain)   //here we define that the intent will share only text it is denoted as category/specifictype
            //so here  our category is text and our type is plain
            //some more example that might showed up in future.
            //"text/html" = HTML formatted text
            //"image/jpeg" = a JPEG image
            //"image/png" = a PNG image
            //"video/mp4" = an MP4 video
            //"audio/mp3" = an MP3 audio file

            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.just_for_fun))  //here we write the messege that we want to share
            //it's like println.
            startActivity(intent)   //here is call the intent.
        }

        val playbtn=findViewById<ImageButton>(R.id.playy)  //here it calls playy from xml file
        playbtn.setOnClickListener{   //here we make it clickable.
            val i=Intent(this, HandlerGameActivity::class.java)   //here we use explicit intent which when we press on the play button
            //it directly opens another activity.in here it open gameactivity.
            startActivity(i)  //here it calls the intent
        }


        val starbtn=findViewById<ImageButton>(R.id.starr)   //here it calls starr from xml file

        starbtn.setOnClickListener {   //here we make the image clickable.
            Toast.makeText(this, getString(R.string.toast_messege_appear_here), Toast.LENGTH_LONG).show()
            //now here there are some alot of things use first lets discuss about toast.
            //toast is like a small messege which is unclickable and it appears near the bottom of the screen.
            //then there are two types of toasts LENGTH_LONG and LENGTH_SHORT.
            //LENGTH_LONG toast messege appear for like 3.5 seconds and
            //LENGTH_SHORT toast messege appear for like 2 seconds

            //in the end we used .show() it is the function which run this code that is toast. without it
            //the code is written but it will never run.
        }

    }
}

