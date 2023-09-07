package com.example.api_in_database_combination.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breed_table")
data class Breed(
    @PrimaryKey
    val breed: String,
    val country: String,
    val origin: String,
    val coat: String,
    val pattern: String
)