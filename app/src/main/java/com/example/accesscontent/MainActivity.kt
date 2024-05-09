package com.example.accesscontent

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var CONTENT_URI = Uri.parse("content://com.demo.user.provider/users")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickShowDetails(view: View?) {
        // inserting complete table details in this text field
        val resultView = findViewById<View>(R.id.res) as TextView

        // creating a cursor object of the
        // content URI
        val cursor = contentResolver.query(Uri.parse("content://com.demo.user.provider/users"), null, null, null, null)

        // iteration of the cursor
        // to print whole table
        if (cursor != null && cursor.moveToFirst()) {
            val strBuild = StringBuilder()
            do {
                val id = cursor.getString(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val age = cursor.getInt(cursor.getColumnIndex("age"))
                val gender = cursor.getString(cursor.getColumnIndex("gender"))

                // Append formatted text to the StringBuilder
                strBuild.append("$id - $name\n")
                strBuild.append("Age: $age\n")
                strBuild.append("Gender: $gender\n\n")
            } while (cursor.moveToNext())
            cursor.close()
            resultView.text = strBuild.toString()
        } else {
            resultView.text = "No Records Found"
        }
    }
}