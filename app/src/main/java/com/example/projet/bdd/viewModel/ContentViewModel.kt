package com.example.projet.bdd.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projet.bdd.BDD
import com.example.projet.bdd.entity.AptitudeEntity
import com.example.projet.bdd.entity.ContentEntity
import kotlinx.coroutines.launch

class ContentViewModel(app: Application) : AndroidViewModel(app) {

    // On crée une instance de la base de données
    private val bdd = BDD.getInstance(app)

    // Cette fonction permet d'insérer une seule aptitude dans la base de données
    // Elle lance une nouvelle Thread pour effectuer l'insertion, car les requêtes sur le thread principal sont interdites
    fun insertOne(content: ContentEntity) {
        Thread() { bdd.ContentDao().insertOne(content) }.start()
    }

    // Cette fonction permet d'insérer plusieurs aptitudes dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(vararg contents: ContentEntity) {
        viewModelScope.launch {
            bdd.ContentDao().insert(*contents)
        }
    }

    // Cette fonction permet d'insérer une liste d'aptitudes dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(contents: List<ContentEntity>) {
        viewModelScope.launch {
            bdd.ContentDao().insert(*contents.toTypedArray())
        }
    }

    // Cette fonction permet de récupérer toutes les aptitudes dans la base de données
    // Elle utilise une Thread pour exécuter la requête, car les requêtes sur le thread principal sont interdites
    // Elle retourne un objet LiveData, qui permet de recevoir des mises à jour automatiques lorsque la base de données change
    fun getAll(): LiveData<List<ContentEntity>>? {
        var liste: LiveData<List<ContentEntity>>? = null;
        var thread = Thread() { liste = bdd.ContentDao().getAll() }
        thread.start()
        thread.join()
        return liste
    }
}