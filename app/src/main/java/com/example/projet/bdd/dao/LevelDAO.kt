package com.example.projet.bdd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projet.bdd.entity.LevelEntity

@Dao
interface LevelDAO {

    @Insert
    fun insertOne(level: LevelEntity) : Long
    @Insert
    fun insert(vararg level: LevelEntity)

    @Update
    fun update(vararg level: LevelEntity)

    @Delete
    fun delete(vararg level: LevelEntity)

    @Query("SELECT * FROM level")
    fun getAll() : LiveData<List<LevelEntity>>

    @Query("SELECT COUNT(*) FROM level")
    fun getNumberOfLevels(): Int

}