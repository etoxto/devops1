package ru.project.lab1

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import ru.project.lab1.controller.NoteController
import ru.project.lab1.model.Note
import ru.project.lab1.service.NoteService
import java.time.LocalDateTime

@WebMvcTest(NoteController::class)
class NoteControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var noteService: NoteService

    private val objectMapper = ObjectMapper()
        .registerModule(JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    private val note = Note(1L, "user", LocalDateTime.now(), "description")

    @Test
    fun `getAllNotes returns list of notes`() {
        whenever(noteService.findAll()).thenReturn(listOf(note))

        mockMvc.perform(get("/notes"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].id").value(note.id))
    }

    @Test
    fun `getNoteById returns note`() {
        whenever(noteService.findById(1L)).thenReturn(note)

        mockMvc.perform(get("/notes/1"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(note.id))
    }

    @Test
    fun `addNote returns created note`() {
        whenever(noteService.save(any())).thenReturn(note)

        mockMvc.perform(
            post("/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(note))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(note.id))
    }

    @Test
    fun `deleteNote returns ok`() {
        doNothing().whenever(noteService).deleteById(1L)

        mockMvc.perform(
            delete("/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("1")
        ).andExpect(status().isOk)
    }
}