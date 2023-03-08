package com.example.projet.bdd.entity.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.projet.bdd.entity.BaseEntity
import com.example.projet.bdd.entity.FormationEntity
import com.example.projet.bdd.entity.SkillEntity

@Entity(
    tableName = "formation_skill_relation",
    primaryKeys = ["id"],
    foreignKeys = [
        ForeignKey(
            entity = FormationEntity::class,
            parentColumns = ["formation_id"],
            childColumns = ["formation_id"]
        ),
        ForeignKey(
            entity = SkillEntity::class,
            parentColumns = ["skill_id"],
            childColumns = ["skill_id"]
        )
    ]
)
data class FormationSkillRelation(
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "formation_id")
    val formationId: Long,

    @ColumnInfo(name = "skill_id")
    val skillId: Long
) : BaseEntity
