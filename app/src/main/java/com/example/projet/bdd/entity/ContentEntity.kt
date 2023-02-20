package com.example.projet.bdd.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "content",foreignKeys = [
    ForeignKey(
        entity = SessionEntity::class,
        parentColumns = ["session_id"],
        childColumns = ["session_id"]),
    ForeignKey(
        entity = AptitudeEntity::class,
        parentColumns = ["aptitude_id"],
        childColumns = ["aptitude_id"])
]
)
class ContentEntity(
    @PrimaryKey(autoGenerate = true) var content_id: Int,
    var session_id: Int,
    var aptitude_id: Int,
) {
}