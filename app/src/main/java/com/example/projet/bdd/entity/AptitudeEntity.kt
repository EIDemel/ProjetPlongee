package com.example.projet.bdd.entity


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "aptitude",foreignKeys = [
    ForeignKey(
        entity = SkillEntity::class,
        parentColumns = ["skill_id"],
        childColumns = ["skill_id"]),
],indices = [Index("skill_id")]
)
class AptitudeEntity(
    @PrimaryKey(autoGenerate = true) var aptitude_id: Long,
    var aptitude_name: String,
    var skill_id: Long,
    var aptitude_deleted: Boolean,
) : BaseEntity {
}