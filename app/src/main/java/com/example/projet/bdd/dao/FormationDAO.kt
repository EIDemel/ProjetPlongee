package com.example.projet.bdd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projet.bdd.entity.FormationEntity

@Dao
interface FormationDAO {
    @Insert
    fun insertOne(formation: FormationEntity) : Long
    @Insert
    fun insert(vararg formation: FormationEntity)

    @Update
    fun update(vararg formation: FormationEntity)

    @Delete
    fun delete(vararg formation: FormationEntity)

    @Query("SELECT * FROM formation")
    fun getAll() : LiveData<List<FormationEntity>>
}