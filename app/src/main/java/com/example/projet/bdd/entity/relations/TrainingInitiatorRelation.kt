package com.example.projet.bdd.entity.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.projet.bdd.entity.BaseEntity
import com.example.projet.bdd.entity.FormationEntity
import com.example.projet.bdd.entity.InitiatorEntity
import com.example.projet.bdd.entity.SkillEntity

@Entity(
    tableName="training_initiator_relation",
    primaryKeys = ["id"],
    foreignKeys = [
        ForeignKey(
            entity = InitiatorEntity::class,
            parentColumns = ["initiator_id"],
            childColumns = ["initiator_id"]
        ),
        ForeignKey(
            entity = FormationEntity::class,
            parentColumns = ["formation_id"],
            childColumns = ["formation_id"]
        )
    ]
)
data class TrainingInitiatorRelation(
    @ColumnInfo(name="id")
    var id: String,

    @ColumnInfo(name = "initiator_id")
    val initiatorId: Long,

    @ColumnInfo(name = "formation_id")
    val formationId: Long
    ) : BaseEntity