package com.example.projet.bdd.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
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
],
    indices = [
        Index("session_id"),
        Index("aptitude_id")
    ]
)
class ContentEntity(
    @PrimaryKey(autoGenerate = true) var content_id: Long,
    var session_id: Long,
    var aptitude_id: Long,
) {
}