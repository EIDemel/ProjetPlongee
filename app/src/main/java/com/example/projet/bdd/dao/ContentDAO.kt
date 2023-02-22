package com.example.projet.bdd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projet.bdd.entity.AptitudeEntity
import com.example.projet.bdd.entity.ContentEntity

@Dao
interface ContentDAO {

    @Insert
    fun insertOne(content: ContentEntity) : Long
    @Insert
    fun insert(vararg content: ContentEntity)
    @Update
    fun update(vararg content: ContentEntity)

    @Delete
    fun delete(vararg content: ContentEntity)

    @Query("SELECT * FROM content")
    fun getAll() : LiveData<List<ContentEntity>>

}