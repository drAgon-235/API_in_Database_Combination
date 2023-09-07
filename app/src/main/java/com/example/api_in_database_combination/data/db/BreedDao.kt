package com.example.api_in_database_combination.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.api_in_database_combination.data.model.Breed

@Dao
interface BreedDao {

    // TODO: insertAll
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(breedData: List<Breed>)

    @Query("SELECT * FROM breed_table")
    fun getAll(): LiveData<List<Breed>>

    @Query("DELETE FROM breed_table")
    suspend fun deleteAll()

}