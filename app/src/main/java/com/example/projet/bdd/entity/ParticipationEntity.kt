package com.example.projet.bdd.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "participation",foreignKeys = [
    ForeignKey(
        entity = ContentEntity::class,
        parentColumns = ["content_id"],
        childColumns = ["content_id"]),
    ForeignKey(
        entity = StudentEntity::class,
        parentColumns = ["student_id"],
        childColumns = ["student_id"]),
    ForeignKey(
        entity = StatusEntity::class,
        parentColumns = ["status_id"],
        childColumns = ["status_id"])],
    indices = [
        Index("content_id"),
        Index("student_id"),
        Index("status_id")
    ]
)
class ParticipationEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var content_id: Long,
    var student_id: Long,
    var status_id: Long,
    var commentary: String,
) : BaseEntity{

}
