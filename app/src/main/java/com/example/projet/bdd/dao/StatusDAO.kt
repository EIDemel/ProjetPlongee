package com.example.projet.bdd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projet.bdd.entity.StatusEntity

@Dao
interface StatusDAO {
    @Insert
    fun insertOne(status: StatusEntity) : Long
    @Insert
    fun insert(vararg status: StatusEntity)

    @Update
    fun update(vararg status: StatusEntity)

    @Delete
    fun delete(vararg status: StatusEntity)

    @Query("SELECT * FROM status")
    fun getAll() : LiveData<List<StatusEntity>>

    @Query("SELECT COUNT(*) FROM status")
    fun getNumberOfStatus(): Int
}