package com.example.projet.bdd.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "level")
class LevelEntity(
    @PrimaryKey(autoGenerate = true) var level_id: Int,
    var level_name: String,
    var level_deleted: Boolean,
    ) {
}