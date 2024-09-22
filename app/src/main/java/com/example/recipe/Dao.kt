package com.example.recipe

import androidx.room.Query
@androidx.room.Dao
interface Dao {
    @Query("SELECT * FROM recipe")
            fun getAll():List<Recipe?>?
}