package com.bmcsolution.jetpacklearning.Architecture.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.room.Room
import com.bmcsolution.jetpacklearning.R
import com.bmcsolution.jetpacklearning.databinding.ActivityRoomBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

class RoomActivity : AppCompatActivity() {
    lateinit var binding: ActivityRoomBinding
    lateinit var database: ContactDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_room)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_room)

        //this is bad practice
        //  database= Room.databaseBuilder(applicationContext,ContactDb::class.java,"ContactDb").build()

        // Singleton Pattern
        database = ContactDb.getDatabase(this)


        database.contactDao().getContact().observe(this, Observer {
            var txt = ""
            for (i in it.indices) {
                var text = "${it[i].Id}      ${it[i].Name}      ${it[i].ContactNumber}      ${it[i].CreatedDate}      ${if (it[i].IsActive==1) "Active" else "Not Active"}"
                txt = "$txt\n$text"

            }
            binding.dataShow.text = txt
        })

        binding.addcont.setOnClickListener {

            GlobalScope.launch {
                database.contactDao().insertContact(Contact(0, binding.name.text.toString(), binding.contactname.text.toString(), Date(),1))
            }

        }
    }
}