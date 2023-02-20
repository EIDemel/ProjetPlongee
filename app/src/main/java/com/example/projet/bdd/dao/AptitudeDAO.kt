package com.example.projet.bdd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projet.bdd.entity.AptitudeEntity

@Dao
interface AptitudeDAO {

    @Insert
    fun insertOne(aptitude: AptitudeEntity) : Long

    @Update
    fun update(vararg aptitude: AptitudeEntity)

    @Delete
    fun delete(vararg aptitude: AptitudeEntity)

    @Query("SELECT * FROM aptitude")
    fun getAll() : LiveData<List<AptitudeEntity>>
}