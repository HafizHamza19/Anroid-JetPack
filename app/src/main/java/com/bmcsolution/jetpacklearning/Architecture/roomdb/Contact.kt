package com.bmcsolution.jetpacklearning.Architecture.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "contacts")
data class Contact(
        @PrimaryKey(autoGenerate = true) val Id: Long,
        val Name: String,
        val ContactNumber: String,
        val CreatedDate: Date,
        val IsActive: Int)
