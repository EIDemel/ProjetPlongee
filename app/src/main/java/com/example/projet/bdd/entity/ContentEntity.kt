    package com.example.projet.bdd.entity

    import androidx.room.Entity
    import androidx.room.ForeignKey
    import androidx.room.Index
    import androidx.room.PrimaryKey


    @Entity(
        tableName = "content",
        foreignKeys = [
            ForeignKey(
                entity = SessionEntity::class,
                parentColumns = ["session_id"],
                childColumns = ["session_id"]
            ),
            ForeignKey(
                entity = AptitudeEntity::class,
                parentColumns = ["aptitude_id"],
                childColumns = ["aptitude_id"]
            )
        ],
        indices = [
            Index("session_id"),  // Créé un index pour la clé étrangère "session_id"
            Index("aptitude_id")  // Créé un index pour la clé étrangère "aptitude_id"
        ]
    )
    class ContentEntity(
        @PrimaryKey(autoGenerate = true) var content_id: Long,  // La clé primaire de l'entité "ContentEntity", générée automatiquement
        var session_id: Long,  // La clé étrangère de l'entité "ContentEntity" vers l'entité "SessionEntity"
        var aptitude_id: Long  // La clé étrangère de l'entité "ContentEntity" vers l'entité "AptitudeEntity"
    ) {
    }