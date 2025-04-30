package ru.project.lab1.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.project.lab1.model.Note
import ru.project.lab1.service.NoteService

@RestController
@RequestMapping(value = ["/notes"])
class NoteController(
    private val noteService: NoteService
) {
    @GetMapping
    fun getAllNotes(): List<Note> = noteService.findAll()

    @GetMapping("/{noteId}")
    fun getNoteById(@PathVariable noteId: Long): Note? = noteService.findById(noteId)

    @PostMapping
    fun addNote(@RequestBody note: Note): Note = noteService.save(note)

    @DeleteMapping
    fun deleteNote(@RequestBody noteId: Long) = noteService.deleteById(noteId)
}