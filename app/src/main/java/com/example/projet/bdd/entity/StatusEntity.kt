package com.example.projet.bdd.entity

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "status")
class StatusEntity(
    @PrimaryKey(autoGenerate = true) var status_id: Long,
    var status_name: String,
    var status_color: String,
) {
}