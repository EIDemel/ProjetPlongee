package com.example.projet.bdd.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projet.bdd.BDD
import com.example.projet.bdd.entity.StatusEntity
import kotlinx.coroutines.launch

class StatusViewModel(app: Application) : AndroidViewModel(app) {

    // On crée une instance de la base de données
    private val bdd = BDD.getInstance(app)

    // Cette fonction permet d'insérer un seul statut dans la base de données
    // Elle lance une nouvelle Thread pour effectuer l'insertion, car les requêtes sur le thread principal sont interdites
    fun insertOne(status: StatusEntity) {
        Thread() { bdd.StatusDao().insertOne(status) }.start()
    }

    // Cette fonction permet d'insérer plusieurs statuts dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(vararg statuses: StatusEntity) {
        viewModelScope.launch {
            bdd.StatusDao().insert(*statuses)
        }
    }

    // Cette fonction permet d'insérer une liste de statuts dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(statuses: List<StatusEntity>) {
        viewModelScope.launch {
            bdd.StatusDao().insert(*statuses.toTypedArray())
        }
    }

    // Cette fonction permet de récupérer tous les statuts dans la base de données
    // Elle utilise une Thread pour exécuter la requête, car les requêtes sur le thread principal sont interdites
    // Elle retourne un objet LiveData, qui permet de recevoir des mises à jour automatiques lorsque la base de données change
    fun getAll(): LiveData<List<StatusEntity>>? {
        var liste: LiveData<List<StatusEntity>>? = null;
        var thread = Thread() { liste = bdd.StatusDao().getAll() }
        thread.start()
        thread.join()
        return liste
    }
}
