package com.example.projet.bdd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projet.bdd.entity.ContentEntity

@Dao
interface ContentDAO {

    // Ajoute un seul élément dans la table content et retourne son identifiant
    @Insert
    fun insertOne(content: ContentEntity) : Long

    // Ajoute plusieurs éléments dans la table content
    @Insert
    fun insert(vararg content: ContentEntity)

    // Met à jour un ou plusieurs éléments de la table content
    @Update
    fun update(vararg content: ContentEntity)

    // Supprime un ou plusieurs éléments de la table content
    @Delete
    fun delete(vararg content: ContentEntity)

    // Récupère tous les éléments de la table content et les retourne sous forme de LiveData
    @Query("SELECT * FROM content")
    fun getAll() : LiveData<List<ContentEntity>>
}