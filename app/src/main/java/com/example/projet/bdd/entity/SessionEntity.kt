package com.example.projet.bdd.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "session",foreignKeys = [
    ForeignKey(
        entity = FomationEntity::class,
        parentColumns = ["formation_id"],
        childColumns = ["formation_id"])]
)
class SessionEntity(
    @PrimaryKey(autoGenerate = true) var session_id: Int,
    var session_date: Date,
    var formation_id: Int,
    var session_deleted: Boolean,
) {

}