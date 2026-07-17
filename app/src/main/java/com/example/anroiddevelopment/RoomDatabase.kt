package com.example.anroiddevelopment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date


//RoomDatabase.kt (your Activity) — actually using the database
//this is the last file that we need to create.
class RoomDatabase : AppCompatActivity() {

    // 🆕 NEW: TextView that will display all our contacts as readable text on screen
    private lateinit var textResults: TextView

    lateinit var database : Roomdatabase_ContactDatabase

    // Declaring our views at class level, so every function in this class can use them
    private lateinit var editId: EditText
    private lateinit var editName: EditText
    private lateinit var editPhone: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_room_database)

        // 🆕 NEW: connecting our new TextView from XML using its id
        textResults = findViewById(R.id.textResults)


        database = Roomdatabase_ContactDatabase.getdatabase(this)

        //This calls your DAO's insert function, which Room automatically converts into real SQL behind the scenes — something like:
        //sql =INSERT INTO contact (id, name, phone) VALUES (NULL, 'Jhon', '1234567');
        //also coroutines used here
        //(commented out - this hardcoded insert used to run every time the screen opened, which caused duplicate entries)

        //why coroutines words like suspend and globalscope is used here.
        //Database operations (reading/writing files) can take a moment, so Room requires them to run off the
        // main thread (to avoid freezing your app's UI). suspend fun marks these as functions that must run inside
        // a coroutine, and GlobalScope.launch { } is what actually starts that coroutine — this is the small bit of
        // coroutine syntax we talked about earlier being unavoidable with Room.

        // Connecting all our EditTexts and Buttons from the XML using their id
        editId = findViewById(R.id.editId)
        editName = findViewById(R.id.editName)
        editPhone = findViewById(R.id.editPhone)

        val btnInsert = findViewById<Button>(R.id.btnInsert)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val btnShow = findViewById<Button>(R.id.btnShow)

        // INSERT - adds a brand new contact into the database
        // We only need name and phone here - id is set to 0 so Room auto-generates a new unique one
        // createdDate uses Date() (right now) since this is a NEW contact being created
        btnInsert.setOnClickListener {
            val name = editName.text.toString()
            val phone = editPhone.text.toString()

            // Room requires database writes to run in the background (off the main thread)
            // GlobalScope.launch starts a coroutine to do that safely
            lifecycleScope.launch {
                database.contactDAO().Insertcontact(
                    RoomDatabaseContact(0, name, phone, Date())
                )
            }

            Toast.makeText(this, getString(R.string.data_inserted), Toast.LENGTH_SHORT).show()
        }

        // UPDATE - modifies an EXISTING contact
        // Room's @Update matches which row to change by looking at the "id" you provide
        // So the user must type the id of the contact they want to edit into editId
        // toLongOrNull() safely converts the typed text into a Long - if it's not a valid number, it returns null instead of crashing
        btnUpdate.setOnClickListener {
            val id = editId.text.toString().toLongOrNull()
            val name = editName.text.toString()
            val phone = editPhone.text.toString()

            // Only proceed if a valid id was actually typed in
            if (id != null) {
                lifecycleScope.launch {
                    database.contactDAO().Updatecontact(
                        RoomDatabaseContact(id, name, phone, Date())
                    )
                }
            }
            Toast.makeText(this, getString(R.string.data_updated), Toast.LENGTH_SHORT).show()
        }

        // DELETE - removes a contact from the database
        // Just like Update, Room's @Delete matches the row to remove using the "id" field
        // name/phone/date values here don't actually matter for deleting - only the id is used to find the row
        btnDelete.setOnClickListener {
            val id = editId.text.toString().toLongOrNull()
            val name = editName.text.toString()
            val phone = editPhone.text.toString()

            if (id != null) {
                lifecycleScope.launch {
                    database.contactDAO().Deletecontact(
                        RoomDatabaseContact(id, name, phone, Date())
                    )
                }
            }

            Toast.makeText(this, getString(R.string.data_deleted), Toast.LENGTH_SHORT).show()
        }

        // SHOW ALL - reads every saved contact from the database and displays them on screen
        // getContact() returns LiveData, which we "observe" - meaning this code runs automatically
        // whenever the data changes (like right after an insert/update/delete happens)
        btnShow.setOnClickListener {
            database.contactDAO().getContact().observe(this) { contacts ->

                // "contacts" here is the fresh list handed to us by observe()
                // joinToString takes the WHOLE list and squashes it into ONE single String
                // "\n\n" is the separator - it gets placed BETWEEN each contact, leaving a blank line for spacing
                // The { contact -> ... } part runs once for EACH contact, deciding how that one contact's text should look
                val displayText = contacts.joinToString("\n\n") { contact ->
                    "ID: ${contact.id}\nName: ${contact.name}\nPhone: ${contact.phone}"
                }

                //  displaying the final joined text block directly on screen using our TextView
                // this replaces the old Log.d() approach - now visible in the app itself instead of Logcat
                textResults.text = displayText
            }
        }

    }

    //now binding the LiveData
    fun getData(view: View) {
        database.contactDAO().getContact().observe(this,{
            Log.d(getString(R.string.zaid),it.toString())
        })
    }
}