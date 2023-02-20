package com.example.projet.bdd.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "participation",foreignKeys = [
    ForeignKey(
        entity = StudentEntity::class,
        parentColumns = ["student_id"],
        childColumns = ["student_id"]),
    ForeignKey(
        entity = ContentEntity::class,
        parentColumns = ["content_id"],
        childColumns = ["content_id"]),
    ForeignKey(
        entity = StatusEntity::class,
        parentColumns = ["status_id"],
        childColumns = ["status_id"])
]
)
class ParticipationEntity(
    @PrimaryKey(autoGenerate = true) var participation_id: Int,
    var student_id: Int,
    var content_id: Int,
    var status_id: Int,
    var commentary: String
) {
}