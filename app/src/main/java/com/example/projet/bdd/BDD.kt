package com.example.projet.bdd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projet.bdd.dao.*
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
    StudentEntity::class], version = 6)
abstract class BDD : RoomDatabase() {
    abstract fun AptitudeDao(): AptitudeDAO
    abstract fun ContentDao(): ContentDAO
    abstract fun FormationDao(): FormationDAO
    abstract fun LevelDao(): LevelDAO
    abstract fun ParticipationDao(): ParticipationDAO
    abstract fun SessionDao(): SessionDAO
    abstract fun SkillDao(): SkillDAO
    abstract fun StatusDao(): StatusDAO
    abstract fun StudentDao(): StudentDAO


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

        fun destroyInstance() {
            instance?.close()
            instance = null
        }
    }
}



