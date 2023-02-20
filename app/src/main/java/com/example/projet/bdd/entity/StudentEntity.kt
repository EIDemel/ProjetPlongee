package com.example.projet.bdd.entity

import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "student", foreignKeys = [
    ForeignKey(
        entity = FomationEntity::class,
        parentColumns = ["formation_id"],
        childColumns = ["formation_id"])]
)
class StudentEntity(
    @PrimaryKey(autoGenerate = true) var student_id: Int,
    var student_name: String,
    var formation_id: Int,
    var student_deleted: Boolean,
    var student_phone: Phone
    ) {
}