package com.bmcsolution.jetpacklearning.Architecture.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bmcsolution.jetpacklearning.Architecture.roomdb.typeConvertors.Converters


@Database(entities = [Contact::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ContactDb : RoomDatabase() {
    abstract fun contactDao(): ContactDAO

    //TODO Singleton pattern
    companion object {

        //Migration old data bhi save rahega new column add krne k bad
        private val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contacts ADD Column IsActive INTEGER NOT NULL DEFAULT(1)")
            }

        }

        //Volatile -> jb bhi instance mai changes hogi ye sub (All Thread) ko point out krta hai jaha bhi ye call hua hoga
        @Volatile
        private var INSTANCE: ContactDb? = null
        fun getDatabase(context: Context): ContactDb {

            if (INSTANCE == null) {
                //synchronized ->Locking Mechanism this approach is thread save
                // agr multiple thread same time DB ko access krne ki koshish krege to jb bhi hamara ek hi instance create hoga
                synchronized(this)
                {
                    INSTANCE = Room.databaseBuilder(context, ContactDb::class.java, "ContactDb")
                            .addMigrations(migration_1_2).build()
                }
            }
            return INSTANCE!!
        }
    }
}