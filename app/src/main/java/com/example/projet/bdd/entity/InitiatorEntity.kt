package com.example.projet.bdd.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "initiator")
class InitiatorEntity(
    @PrimaryKey(autoGenerate = true) var initiator_id: Long,
    var name: String,
    var email: String,
    var password: String,
    var director: Boolean,
    var levelId: Long,
    var deleted: Boolean,
) : BaseEntity {
}