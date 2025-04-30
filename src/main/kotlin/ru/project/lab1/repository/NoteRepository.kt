package ru.project.lab1.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.project.lab1.model.Note

@Repository
interface NoteRepository : JpaRepository<Note, Long> {}