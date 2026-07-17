package com.example.anroiddevelopment

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

//Preferences DataStore
//What it is: Google's modern replacement for SharedPreferences — same basic job (saving small key-value pairs), but done more safely.
//How it works: Saves data asynchronously (runs in the background), avoids freezing the app, and properly reports errors instead of failing silently.
//Example use: Same kinds of things as SharedPreferences — dark mode setting, small saved values, flags.
//Downside: Requires understanding Coroutines (suspend, lifecycleScope.launch, Flow) — there's no way around this, it's built into how the library works.
//Coroutines needed? Yes, always.


// This creates our DataStore - think of it as a small file/notebook saved on the phone
// "Context.dataStore" means we're attaching this notebook to any Context (like our Activity)
// "by preferencesDataStore(...)" is special Kotlin syntax that sets up and remembers this notebook automatically
// name = "settings" -> this is just the file name for our notebook, saved internally on the device
val android.content.Context.dataStore by preferencesDataStore(name = "settings")

// Our Activity class - represents this one screen in the app
class preferencesdatastore : AppCompatActivity() {

    // Declaring variables for each view - "lateinit" means: no value yet, but we PROMISE to set one before using it
    private lateinit var nameText: TextView
    private lateinit var ageText: TextView
    private lateinit var enterName: EditText
    private lateinit var enterAge: EditText
    private lateinit var saveButton: Button

    // This is a "key" - basically a label/name tag we use to save and find our name value later
    // stringPreferencesKey means: "this key will point to a value that is a String"
    private val NAME_KEY = stringPreferencesKey("name_key")

    // Same idea as above, but this key/label is specifically for the age value
    private val AGE_KEY = stringPreferencesKey("age_key")

    // This function runs automatically the very first time this screen is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)   // lets the parent class (AppCompatActivity) do its normal setup first
        enableEdgeToEdge()                   // makes the app content extend behind system bars (status bar/nav bar)
        setContentView(R.layout.activity_preference_data_store)   // loads our XML layout file for this screen

        // Finding each view from the XML using its id, and connecting it to our variables above
        nameText = findViewById(R.id.name)
        ageText = findViewById(R.id.age)
        enterName = findViewById(R.id.entername)
        enterAge = findViewById(R.id.enterage)
        saveButton = findViewById(R.id.save)

        // Calls our own function below, to check if there's any saved data and show it immediately
        loadData()

        // setOnClickListener -> runs the code inside { } whenever this button is tapped
        saveButton.setOnClickListener {
            val newName = enterName.text.toString()
            val newAge = enterAge.text.toString()

            saveData(newName, newAge)

            // Instantly show the new values, no need to wait on DataStore to read it back
            nameText.text = newName
            ageText.text = newAge

        }
    }

    // A function we made ourselves - its job is to SAVE data into DataStore
    // Takes two pieces of info as input: the name and age typed by the user
    private fun saveData(name: String, age: String) {

        // "lifecycleScope.launch { }" is required boilerplate for DataStore -
        // it just means "run the code inside safely in the background, tied to this screen's lifecycle"
        lifecycleScope.launch {

            // "dataStore.edit { settings -> }" -> opens our notebook so we can WRITE into it
            // "settings" here represents everything currently saved (like the notebook's current pages)
            dataStore.edit { settings ->

                // Writing the name value under our NAME_KEY label
                settings[NAME_KEY] = name

                // Writing the age value under our AGE_KEY label
                settings[AGE_KEY] = age
            }
        }
    }

    // A function we made ourselves - its job is to READ/LOAD previously saved data
    private fun loadData() {

        // Same background-running requirement as before
        lifecycleScope.launch {

            // "dataStore.data.first()" -> grabs the most recent saved data from our notebook
            // this pauses briefly until the data is ready (that's normal, and handled safely for us)
            val preferences = dataStore.data.first()

            // Looking up our NAME_KEY inside the saved data
            // "?: "Name"" -> if nothing was ever saved yet (first time opening app), use "Name" as a default instead
            val savedName = preferences[NAME_KEY] ?: "Name"

            // Same idea, but for age - defaults to "Age" if nothing was saved yet
            val savedAge = preferences[AGE_KEY] ?: "Age"

            // Displaying the saved name on the nameText TextView
            nameText.text = savedName

            // Displaying the saved age on the ageText TextView
            ageText.text = savedAge
        }
    }
}