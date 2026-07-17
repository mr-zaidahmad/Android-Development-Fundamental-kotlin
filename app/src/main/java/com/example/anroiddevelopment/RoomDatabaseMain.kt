package com.example.anroiddevelopment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.anroiddevelopment.databinding.ActivityRoomDatabaseMainBinding
import kotlinx.coroutines.launch

class RoomDatabaseMain : AppCompatActivity() {

    // Room database object.
    // Through this object we can access our DAO and perform
    // database operations like Insert, Update, Delete and Read.
    lateinit var database: RoomDatabaseEmployee_EmployeeDatabase

    // ViewBinding object.
    // It allows us to access all the views in our XML
    // without using findViewById().
    private lateinit var binding: ActivityRoomDatabaseMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate (connect) the XML layout using ViewBinding.
        binding = ActivityRoomDatabaseMainBinding.inflate(layoutInflater)

        // Display the XML layout on the screen.
        setContentView(binding.root)

        // Get the Room database.
        // If the database already exists, Room returns the existing one.
        // Otherwise, Room creates it for the first time.
        database = RoomDatabaseEmployee_EmployeeDatabase.getDatabase(this)

        // ---------------------------------------------------------
        // OBSERVER
        // ---------------------------------------------------------
        // Observe() keeps listening to the employee table.
        // Whenever data is inserted, updated or deleted,
        // this block is called automatically.
        //
        // This is why we no longer have to manually call
        // getAllEmployee() after every database operation.
        database.employeeDAO().getAllEmployee().observe(this) { employees ->

            // This String will store all employee information.
            // We'll display this String inside the TextView.
            var result = ""

            // employees is a List<RoomDatabaseEmployee>.
            // Loop through every employee one by one.
            for (emp in employees) {

                // Add each employee's information to the String.
                result += getString(R.string.id, emp.id)
                result += getString(R.string.name, emp.name)
                result += getString(R.string.department, emp.department)
            }

            // Display the final String on the screen.
            // Since we're inside observe(), the UI refreshes
            // automatically whenever the database changes.
            binding.textViewEmployees.text = result
        }

        // ---------------------------------------------------------
        // INSERT
        // ---------------------------------------------------------
        // Runs whenever the Save button is clicked.
        binding.savebutton.setOnClickListener {

            // Read the values entered by the user.
            val name = binding.EditText1.text.toString()
            val department = binding.DepartmentText.text.toString()

            // Create an Employee object.
            // Notice that we DON'T pass an id.
            // That's because Room generates it automatically
            // using @PrimaryKey(autoGenerate = true).
            val employee = RoomDatabaseEmployee(
                name = name,
                department = department
            )

            // insertEmployee() is a suspend function.
            // Therefore it must run inside a coroutine.
            lifecycleScope.launch {

                // Save the employee into the Room database.
                database.employeeDAO().insertEmployee(employee)

                // We don't manually refresh the TextView here.
                // LiveData notices that the database changed,
                // then automatically calls observe().
            }

            // Optional:
            // Clear the EditTexts after saving.
            binding.EditText1.text.clear()
            binding.DepartmentText.text.clear()
        }

        // ---------------------------------------------------------
        // UPDATE
        // ---------------------------------------------------------
        // Runs whenever the Update button is clicked.
        binding.UpdateButton.setOnClickListener {

            // Read the id, name and department from the EditTexts.
            val id = binding.ID.text.toString().toInt()
            val name = binding.EditText1.text.toString()
            val department = binding.DepartmentText.text.toString()

            // Create an Employee object.
            // Unlike Insert, we MUST provide the id.
            // Room uses this id to find which row should be updated.
            val employee = RoomDatabaseEmployee(
                id = id,
                name = name,
                department = department
            )

            // updateEmployee() is also a suspend function.
            lifecycleScope.launch {

                // Update the matching employee in the database.
                database.employeeDAO().updateEmployee(employee)

                // LiveData automatically updates the UI.
            }

            // Optional:
            // Clear the EditTexts after updating.
            binding.ID.text.clear()
            binding.EditText1.text.clear()
            binding.DepartmentText.text.clear()
        }

        // ---------------------------------------------------------
        // DELETE
        // ---------------------------------------------------------
        // Runs whenever the Delete button is clicked.
        binding.DeleteButton.setOnClickListener {

            // Read the id, name and department.
            val id = binding.ID.text.toString().toInt()
            val name = binding.EditText1.text.toString()
            val department = binding.DepartmentText.text.toString()

            // Create an Employee object.
            // Room mainly uses the id (Primary Key)
            // to identify which row should be deleted.
            val employee = RoomDatabaseEmployee(
                id = id,
                name = name,
                department = department
            )

            // deleteEmployee() is also a suspend function.
            lifecycleScope.launch {

                // Delete the employee from the database.
                database.employeeDAO().deleteEmployee(employee)

                // Again, LiveData automatically refreshes the UI.
            }

            // Optional:
            // Clear the EditTexts after deleting.
            binding.ID.text.clear()
            binding.EditText1.text.clear()
            binding.DepartmentText.text.clear()
        }
    }
}