package com.example.projet.bdd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projet.bdd.entity.LevelEntity

@Dao
interface LevelDAO {

    @Insert
    fun insertOne(level: LevelEntity) : Int

    @Update
    fun update(vararg level: LevelEntity)

    @Delete
    fun delete(vararg level: LevelEntity)

    @Query("SELECT * FROM level")
    fun getAll() : LiveData<List<LevelEntity>>

}