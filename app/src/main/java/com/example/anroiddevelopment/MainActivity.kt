package com.example.anroiddevelopment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.anroiddevelopment.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.Firebase
import com.google.firebase.initialize
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    //to use firebase in our app first we make variable to connect it to the FirebaseAnalytics import
    // private lateinit var firebaseAnalytics: FirebaseAnalytics

    //variables and taking functions and assigning it to the variables for Toolbar.

    //to set up toolbar and navigation bar we need three functionality to take from android.
    //1:Drawerlayout
    //2:Toolbar
    //3:ActionBarToggle
    //so for that we take it from android and assign it to the variable so that se can access it.
    //we can't access it directly that why we store it in the variable.
    private lateinit var drawerLayout: DrawerLayout //the container that holds your hamburger menu (the sliding drawer itself)
    private lateinit var toolbar: Toolbar  //the top bar of your app (where the hamburger icon and title sit)
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle  // the actual hamburger icon button, plus the animation that makes it open/close the drawer

    //NOw lets add navigationView si fir that first lets assign NavigationView to a variable.
    private lateinit var navigationview: NavigationView

    //for viewbinding
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Using firebase analytics for my retrofit Button
       // firebaseAnalytics = FirebaseAnalytics.getInstance(this)


        //sending push notification to firebase.
//        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Log.w("FCM", "Fetching FCM token failed", task.exception)
//                return@addOnCompleteListener
//            }
//
//            // This is your device's unique token
//            val token = task.result
//            Log.d("FCM", "Token: $token")
//        }

          setUpToolbar()

      //NOW ADDING THE NAVIGATION MENU TO THE TOOLBAR AND DRAWER.
        navigationview=findViewById(R.id.navigation_menu)  //find the NavigationView from XML using its id

        navigationview.setNavigationItemSelectedListener { item ->  //listen for when the user taps any item inside the drawer menu

            when(item.itemId){   //check WHICH item was tapped, using its id

                R.id.nav_home ->{   //if home was tapped popu toast
                    Toast.makeText(this,"home Clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_ios ->{   //if ios was tapped popu toast
                    Toast.makeText(this,"Ios Clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_fun ->{    //if fun staff was tapped popu toast
                    Toast.makeText(this,"Fun Staff Clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_updates ->{   //if updates was tapped popu toast
                    Toast.makeText(this,"Updates Clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_settings ->{   //if setting was tapped popu toast
                    Toast.makeText(this,"Setting Clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_aboutus ->{   //if About us was tapped popu toast
                    Toast.makeText(this,"Aboutus Clicked", Toast.LENGTH_SHORT).show()
                }
            }
            false  //false means don't auto-highlight/select the tapped item visually
        }


        binding.glide.setOnClickListener {
            startActivity(Intent(this, GlideActivity::class.java))

        }

        binding.Volley.setOnClickListener {
            startActivity(Intent(this, VollyLibrary::class.java))

        }

            binding.Recycler.setOnClickListener {
                startActivity(Intent(this, RecyclerViewMain::class.java))

        }

        binding.Handler.setOnClickListener {
            startActivity( Intent(this, HandlerMainActivity::class.java))

        }
        binding.Retrofit.setOnClickListener {

             //Adding firebase on the retrofit button
            //firebaseAnalytics.logEvent("retrofit_button_clicked", null)
            //throw RuntimeException("Test Crash")  //ok so enabled it only to
            startActivity(Intent(this, RetrofitMainActivity::class.java))   //we can also write it like this
        }
        binding.infinitescrolling.setOnClickListener {
            startActivity(Intent(this, infinitescrolling::class.java))
        }
        binding.Fragments.setOnClickListener {
            startActivity(Intent(this, Fragments::class.java))
        }

        binding.ActivityLifeCycle.setOnClickListener {
            startActivity(Intent(this, ActivityLifecycle::class.java))

        }
        binding.SharedPrefrences.setOnClickListener {
            startActivity(Intent(this, SharedPreferences::class.java))
        }

    }


 // SETTING UP TOOLBAR AND NAVIGATION DRAWER (we are doing it here and not creating other activity for it cuz it's done in the main activity. )
    private fun setUpToolbar() {
     drawerLayout = findViewById(R.id.drawerLayout)  //we can use bindiing. instead of findviewbyid but i use find...... cuz just
                 // to tell that we can use findview...... too.
     toolbar = findViewById(R.id.toolbar)
     setSupportActionBar(toolbar)   //it is used to tell the android to use only
     //that toolbar that we create in our xml layout not the default toolbar.
     //and we access it by passing it's id.

     actionBarDrawerToggle =
         ActionBarDrawerToggle(   //This creates the actual hamburger icon and links it to your drawer and toolbar.
             // Think of it like: "Put a hamburger icon on this toolbar, and connect it to this drawer, so tapping it opens/closes the drawer."

             this,
             drawerLayout,
             toolbar,
             R.string.app_name,
             R.string.app_name  //The two R.string.app_name values are just accessibility text
             // (spoken by screen readers) — one for "drawer open" and one for "drawer closed." Normally these would be two different string messages,
             // but here both are just using the app name as a placeholder
         )
     drawerLayout.addDrawerListener(actionBarDrawerToggle)  //This connects the toggle to the drawer, so it can detect and react to the drawer
     // opening/closing (like animating the hamburger icon into an arrow)

     actionBarDrawerToggle.syncState()   //This makes sure the hamburger icon shows up correctly the first time the screen loads — without this line,
     //the icon might not appear until you interact with the drawer once.


     // this .addDrawerListener(actionBarDrawerToggle) and actionBarDrawerToggle.syncState()
     // is a helper class   that we got from the library that we add in the manifest file.it do all the work for drawerlayout and
     //toolbar to sync them. and manage all the work for them. like drawer open or close everything is
     //manage by it.
 }


}