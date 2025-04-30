package ru.project.lab1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import ru.project.lab1.model.Note
import ru.project.lab1.repository.NoteRepository
import ru.project.lab1.service.NoteService
import java.time.LocalDateTime
import java.util.*

class NoteServiceTest {

    private val noteRepository: NoteRepository = mock()
    private val noteService = NoteService(noteRepository)

    private val sampleNote = Note(1L, "user", LocalDateTime.now(), "test")

    @Test
    fun `findAll should return notes`() {
        whenever(noteRepository.findAll()).thenReturn(listOf(sampleNote))

        val result = noteService.findAll()

        assertEquals(1, result.size)
        assertEquals(sampleNote, result[0])
    }

    @Test
    fun `findById should return note`() {
        whenever(noteRepository.findById(1L)).thenReturn(Optional.of(sampleNote))

        val result = noteService.findById(1L)

        assertEquals(sampleNote, result)
    }

    @Test
    fun `save should return saved note`() {
        whenever(noteRepository.save(sampleNote)).thenReturn(sampleNote)

        val result = noteService.save(sampleNote)

        assertEquals(sampleNote, result)
    }

    @Test
    fun `deleteById should call repository`() {
        doNothing().whenever(noteRepository).deleteById(1L)

        noteService.deleteById(1L)

        verify(noteRepository, times(1)).deleteById(1L)
    }
}