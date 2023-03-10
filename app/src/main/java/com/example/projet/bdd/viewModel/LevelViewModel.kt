package com.example.projet.bdd.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projet.bdd.BDD
import com.example.projet.bdd.entity.LevelEntity
import kotlinx.coroutines.launch

class LevelViewModel(app: Application) : AndroidViewModel(app) {

    // On crée une instance de la base de données
    private val bdd = BDD.getInstance(app)

    // Cette fonction permet d'insérer un seul niveau dans la base de données
    // Elle lance une nouvelle Thread pour effectuer l'insertion, car les requêtes sur le thread principal sont interdites
    fun insertOne(level: LevelEntity) {
        Thread() { bdd.LevelDao().insertOne(level) }.start()
    }

    // Cette fonction permet d'insérer plusieurs niveaux dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(vararg levels: LevelEntity) {
        viewModelScope.launch {
            bdd.LevelDao().insert(*levels)
        }
    }

    // Cette fonction permet d'insérer une liste de niveaux dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(levels: List<LevelEntity>) {
        viewModelScope.launch {
            bdd.LevelDao().insert(*levels.toTypedArray())
        }
    }

    // Cette fonction permet de récupérer tous les niveaux dans la base de données
    // Elle utilise une Thread pour exécuter la requête, car les requêtes sur le thread principal sont interdites
    // Elle retourne un objet LiveData, qui permet de recevoir des mises à jour automatiques lorsque la base de données change
    fun getAll(): LiveData<List<LevelEntity>>? {
        var liste: LiveData<List<LevelEntity>>? = null;
        var thread = Thread() { liste = bdd.LevelDao().getAll() }
        thread.start()
        thread.join()
        return liste
    }
}
