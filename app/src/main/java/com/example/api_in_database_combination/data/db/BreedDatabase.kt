package com.example.api_in_database_combination.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.api_in_database_combination.data.model.Breed

@Database(entities = [Breed::class], version = 1)
abstract class BreedDatabase : RoomDatabase() {

    abstract val breedDao: BreedDao

    companion object {
        private lateinit var dbInstance: BreedDatabase

        fun getDatabase(context: Context): BreedDatabase {
            synchronized(this) {
                // Initialisiere Datenbank
                if (!this::dbInstance.isInitialized) {
                    dbInstance = Room.databaseBuilder(
                        context.applicationContext,
                        BreedDatabase::class.java,
                        "breed_database"
                    ).build()   // Ohne AllowMainThreadQueries (cleaner)
                }
                return dbInstance
            }
        }
    }
}