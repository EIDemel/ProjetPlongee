package com.example.projet.bdd.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projet.bdd.BDD
import com.example.projet.bdd.entity.SessionEntity
import kotlinx.coroutines.launch

class SessionViewModel(app: Application) : AndroidViewModel(app) {

    // On crée une instance de la base de données
    private val bdd = BDD.getInstance(app)

    // Cette fonction permet d'insérer une seule session dans la base de données
    // Elle lance une nouvelle Thread pour effectuer l'insertion, car les requêtes sur le thread principal sont interdites
    fun insertOne(session: SessionEntity) {
        Thread() { bdd.SessionDao().insertOne(session) }.start()
    }

    // Cette fonction permet d'insérer plusieurs sessions dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(vararg sessions: SessionEntity) {
        viewModelScope.launch {
            bdd.SessionDao().insert(*sessions)
        }
    }

    // Cette fonction permet d'insérer une liste de sessions dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(sessions: List<SessionEntity>) {
        viewModelScope.launch {
            bdd.SessionDao().insert(*sessions.toTypedArray())
        }
    }

    // Cette fonction permet de récupérer toutes les sessions dans la base de données
    // Elle utilise une Thread pour exécuter la requête, car les requêtes sur le thread principal sont interdites
    // Elle retourne un objet LiveData, qui permet de recevoir des mises à jour automatiques lorsque la base de données change
    fun getAll(): LiveData<List<SessionEntity>>? {
        var liste: LiveData<List<SessionEntity>>? = null;
        var thread = Thread() { liste = bdd.SessionDao().getAll() }
        thread.start()
        thread.join()
        return liste
    }
}
