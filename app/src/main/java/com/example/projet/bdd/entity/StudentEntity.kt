package com.example.projet.bdd.entity

import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "student", foreignKeys = [
    ForeignKey(
        entity = FormationEntity::class,
        parentColumns = ["formation_id"],
        childColumns = ["formation_id"])],
    indices = [
        Index("formation_id")
    ]
)
class StudentEntity(
    @PrimaryKey(autoGenerate = true) var student_id: Int,
    var student_name: String,
    var formation_id: Int,
    var student_deleted: Boolean,
    var student_phone: Int
    ) {
}