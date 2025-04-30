package ru.project.lab1.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.project.lab1.model.Note
import ru.project.lab1.repository.NoteRepository

@Service
class NoteService(
    private val noteRepository: NoteRepository
) {
    fun findAll(): List<Note> = noteRepository.findAll()

    fun findById(id: Long): Note? = noteRepository.findByIdOrNull(id)

    fun save(note: Note): Note = noteRepository.save(note)

    fun deleteById(id: Long) = noteRepository.deleteById(id)
}