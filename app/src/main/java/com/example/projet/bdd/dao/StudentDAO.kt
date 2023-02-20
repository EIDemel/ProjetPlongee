package com.example.projet.bdd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projet.bdd.entity.StudentEntity

@Dao
interface StudentDAO {
    @Insert
    fun insertOne(student: StudentEntity) : Int

    @Update
    fun update(vararg student: StudentEntity)

    @Delete
    fun delete(vararg student: StudentEntity)

    @Query("SELECT * FROM student")
    fun getAll() : LiveData<List<StudentEntity>>
}