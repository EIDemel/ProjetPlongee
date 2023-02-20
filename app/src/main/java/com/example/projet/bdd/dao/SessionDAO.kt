package com.example.projet.bdd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projet.bdd.entity.ContentEntity
import com.example.projet.bdd.entity.SessionEntity

@Dao
interface SessionDAO {

    @Insert
    fun insertOne(session: SessionEntity) : Int

    @Update
    fun update(vararg session: SessionEntity)

    @Delete
    fun delete(vararg session: SessionEntity)

    @Query("SELECT * FROM session")
    fun getAll() : LiveData<List<SessionEntity>>

}