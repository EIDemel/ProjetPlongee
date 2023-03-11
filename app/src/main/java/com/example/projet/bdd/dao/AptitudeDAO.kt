package com.example.projet.bdd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projet.bdd.entity.AptitudeEntity

@Dao
interface AptitudeDAO {

    // Insertion d'une seule AptitudeEntity dans la base de données
    // Retourne l'identifiant de la nouvelle ligne insérée
    @Insert
    fun insertOne(aptitude: AptitudeEntity): Long

    // Insertion d'un ou plusieurs AptitudeEntity dans la base de données
    @Insert
    fun insert(vararg aptitude: AptitudeEntity)

    // Mise à jour d'un ou plusieurs AptitudeEntity dans la base de données
    @Update
    fun update(vararg aptitude: AptitudeEntity)

    // Suppression d'un ou plusieurs AptitudeEntity dans la base de données
    @Delete
    fun delete(vararg aptitude: AptitudeEntity)

    // Récupération de toutes les AptitudeEntity dans la base de données
    // La méthode retourne un objet LiveData qui permet d'observer les résultats en temps réel
    @Query("SELECT * FROM aptitude")
    fun getAll(): LiveData<List<AptitudeEntity>>
}
