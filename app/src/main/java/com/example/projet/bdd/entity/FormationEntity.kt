package com.example.projet.bdd.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "formation"
    ,foreignKeys = [
    ForeignKey(
        entity = LevelEntity::class,
        parentColumns = ["level_id"],
        childColumns = ["level_id"])]
)
class FormationEntity(
    @PrimaryKey(autoGenerate = true) var formation_id: Long,
    var formation_name: String,
    var formation_deleted: Boolean,
    @ColumnInfo(name = "level_id",
        index = true)
    var level_id: Long
)  : BaseEntity{

}