package com.example.projet.bdd.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projet.bdd.BDD
import com.example.projet.bdd.entity.SkillEntity
import kotlinx.coroutines.launch

class SkillViewModel(app: Application) : AndroidViewModel(app) {

    // On crée une instance de la base de données
    private val bdd = BDD.getInstance(app)

    // Cette fonction permet d'insérer une seule compétence dans la base de données
    // Elle lance une nouvelle Thread pour effectuer l'insertion, car les requêtes sur le thread principal sont interdites
    fun insertOne(skill: SkillEntity) {
        Thread() { bdd.SkillDao().insertOne(skill) }.start()
    }

    // Cette fonction permet d'insérer plusieurs compétences dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(vararg skills: SkillEntity) {
        viewModelScope.launch {
            bdd.SkillDao().insert(*skills)
        }
    }

    // Cette fonction permet d'insérer une liste de compétences dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(skills: List<SkillEntity>) {
        viewModelScope.launch {
            bdd.SkillDao().insert(*skills.toTypedArray())
        }
    }

    // Cette fonction permet de récupérer toutes les compétences dans la base de données
    // Elle utilise une Thread pour exécuter la requête, car les requêtes sur le thread principal sont interdites
    // Elle retourne un objet LiveData, qui permet de recevoir des mises à jour automatiques lorsque la base de données change
    fun getAll(): LiveData<List<SkillEntity>>? {
        var liste: LiveData<List<SkillEntity>>? = null;
        var thread = Thread() { liste = bdd.SkillDao().getAll() }
        thread.start()
        thread.join()
        return liste
    }
}
