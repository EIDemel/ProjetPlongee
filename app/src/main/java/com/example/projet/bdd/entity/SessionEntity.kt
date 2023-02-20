package com.example.projet.bdd.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "session",foreignKeys = [
    ForeignKey(
        entity = FormationEntity::class,
        parentColumns = ["formation_id"],
        childColumns = ["formation_id"])],
    indices = [
        Index("formation_id"),
    ]
)
class SessionEntity(
    @PrimaryKey(autoGenerate = true) var session_id: Long,
    var session_date: String,
    var formation_id: Long,
    var session_deleted: Boolean,
) {

}