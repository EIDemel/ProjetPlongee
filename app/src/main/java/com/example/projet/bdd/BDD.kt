package com.example.projet.bdd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projet.bdd.dao.AptitudeDAO
import com.example.projet.bdd.dao.ContentDAO
import com.example.projet.bdd.entity.*

@Database(entities = [
    AptitudeEntity::class,
    ContentEntity::class,
    FormationEntity::class,
    LevelEntity::class,
    ParticipationEntity::class,
    SessionEntity::class,
    SkillEntity::class,
    StatusEntity::class,
    StudentEntity::class, ], version = 5)
abstract class BDD : RoomDatabase() {

    companion object {
        private var instance: BDD? = null

        fun getInstance(context: Context): BDD {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    BDD::class.java,
                    "BDD_plongeurs"
                ).build()
            }
            return instance!!
        }
    }
}



