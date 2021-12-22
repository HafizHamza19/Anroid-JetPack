package com.bmcsolution.jetpacklearning.Architecture.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*
@Dao
interface ContactDAO {
    @Insert
   suspend fun insertContact(contact: Contact)

   @Update
   suspend fun update(contact: Contact)

   @Delete
   suspend fun delete(contact: Contact)

   @Query("SELECT * FROM contacts")
    fun getContact():LiveData<List<Contact>>
}