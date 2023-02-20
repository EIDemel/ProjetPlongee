package com.example.projet.bdd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projet.bdd.entity.SkillEntity

@Dao
interface SkillDAO {
    @Insert
    fun insertOne(skill: SkillEntity) : Long

    @Update
    fun update(vararg skill: SkillEntity)

    @Delete
    fun delete(vararg skill: SkillEntity)

    @Query("SELECT * FROM skill")
    fun getAll() : LiveData<List<SkillEntity>>
}