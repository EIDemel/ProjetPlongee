package com.example.projet.bdd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projet.bdd.entity.ParticipationEntity

@Dao
interface ParticipationDAO {

    @Insert
    fun insertOne(participation: ParticipationEntity) : Long
    @Insert
    fun insert(vararg participation: ParticipationEntity)

    @Update
    fun update(vararg participation: ParticipationEntity)

    @Delete
    fun delete(vararg participation: ParticipationEntity)

    @Query("SELECT * FROM participation")
    fun getAll() : LiveData<List<ParticipationEntity>>

    @Query("SELECT COUNT(*) FROM participation")
    fun getNumberOfParticipations(): Int

}