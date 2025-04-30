package ru.project.lab1.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class Note(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var owner: String,
    var createdDate: LocalDateTime,
    var description: String
) {
    constructor() : this(null, "", LocalDateTime.now(), "")
}