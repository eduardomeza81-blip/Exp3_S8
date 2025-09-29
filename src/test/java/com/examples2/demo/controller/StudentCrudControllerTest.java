package com.examples2.demo.controller;

import com.examples2.demo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StudentController.class)
class StudentCrudControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void getAllStudents_devuelveListaJson() throws Exception {
        when(studentService.getAllStudents()).thenReturn(java.util.Collections.emptyList());

        mockMvc.perform(get("/students").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    void getStudentById_devuelveObjetoJson() throws Exception {
        when(studentService.getStudentById(1L)).thenReturn(java.util.Optional.empty());

        mockMvc.perform(get("/students/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // Optional.empty() -> "null"
                .andExpect(content().string("null"));
    }

    @Test
    void delete_eliminaYRetorna204() throws Exception {
        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isNoContent());
        verify(studentService).eliminar(1L);
    }
}
