package com.example.projet.bdd.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "skill",foreignKeys = [
    ForeignKey(
        entity = LevelEntity::class,
        parentColumns = ["level_id"],
        childColumns = ["level_id"]),
],
    indices = [
        Index("level_id")
    ]
)
class SkillEntity(
    @PrimaryKey(autoGenerate = true) var skill_id: Long,
    var skill_name: String,
    var skill_deleted: Boolean,
    var level_id: Long,
) {
}