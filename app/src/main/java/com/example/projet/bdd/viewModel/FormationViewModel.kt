package com.example.projet.bdd.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projet.bdd.BDD
import com.example.projet.bdd.entity.FormationEntity
import kotlinx.coroutines.launch

class FormationViewModel(app: Application) : AndroidViewModel(app) {

    // On crée une instance de la base de données
    private val bdd = BDD.getInstance(app)

    // Cette fonction permet d'insérer une seule formation dans la base de données
    // Elle lance une nouvelle Thread pour effectuer l'insertion, car les requêtes sur le thread principal sont interdites
    fun insertOne(formation: FormationEntity) {
        Thread() { bdd.FormationDao().insertOne(formation) }.start()
    }

    // Cette fonction permet d'insérer plusieurs formations dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(vararg formations: FormationEntity) {
        viewModelScope.launch {
            bdd.FormationDao().insert(*formations)
        }
    }

    // Cette fonction permet d'insérer une liste de formations dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(formations: List<FormationEntity>) {
        viewModelScope.launch {
            bdd.FormationDao().insert(*formations.toTypedArray())
        }
    }

    // Cette fonction permet de récupérer toutes les formations dans la base de données
    // Elle utilise une Thread pour exécuter la requête, car les requêtes sur le thread principal sont interdites
    // Elle retourne un objet LiveData, qui permet de recevoir des mises à jour automatiques lorsque la base de données change
    fun getAll(): LiveData<List<FormationEntity>>? {
        var liste: LiveData<List<FormationEntity>>? = null;
        var thread = Thread() { liste = bdd.FormationDao().getAll() }
        thread.start()
        thread.join()
        return liste
    }
}