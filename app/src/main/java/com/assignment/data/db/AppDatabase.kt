package com.assignment.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.assignment.data.About
import com.assignment.data.db.entities.AboutDao

@Database(entities = [About::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAboutDao(): AboutDao

    companion object {
        private var instance: AppDatabase? = null

        fun getDbInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::
                        class.java,
                        "assignment.db"
                    ).build()
                }
            }
            return instance!!
        }
    }
}