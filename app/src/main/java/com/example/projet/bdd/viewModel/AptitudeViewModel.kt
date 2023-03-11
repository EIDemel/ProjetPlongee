package com.example.projet.bdd.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projet.bdd.BDD
import com.example.projet.bdd.entity.AptitudeEntity
import kotlinx.coroutines.launch

class AptitudeViewModel(app: Application) : AndroidViewModel(app) {

    // On crée une instance de la base de données
    private val bdd = BDD.getInstance(app)

    // Cette fonction permet d'insérer une seule aptitude dans la base de données
    // Elle lance une nouvelle Thread pour effectuer l'insertion, car les requêtes sur le thread principal sont interdites
    fun insertOne(aptitude: AptitudeEntity) {
        Thread() { bdd.AptitudeDao().insertOne(aptitude) }.start()
    }

    // Cette fonction permet d'insérer plusieurs aptitudes dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(vararg aptitudes: AptitudeEntity) {
        viewModelScope.launch {
            bdd.AptitudeDao().insert(*aptitudes)
        }
    }

    // Cette fonction permet d'insérer une liste d'aptitudes dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(aptitudes: List<AptitudeEntity>) {
        viewModelScope.launch {
            bdd.AptitudeDao().insert(*aptitudes.toTypedArray())
        }
    }

    // Cette fonction permet de récupérer toutes les aptitudes dans la base de données
    // Elle utilise une Thread pour exécuter la requête, car les requêtes sur le thread principal sont interdites
    // Elle retourne un objet LiveData, qui permet de recevoir des mises à jour automatiques lorsque la base de données change
    fun getAll(): LiveData<List<AptitudeEntity>>? {
        var liste: LiveData<List<AptitudeEntity>>? = null;
        var thread = Thread() { liste = bdd.AptitudeDao().getAll() }
        thread.start()
        thread.join()
        return liste
    }
}
