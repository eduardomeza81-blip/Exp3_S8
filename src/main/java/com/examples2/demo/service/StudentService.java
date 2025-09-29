package com.examples2.demo.service;
import com.examples2.demo.Student;
import com.examples2.demo.dto.GananciaDiaDTO;
import java.util.List;
import java.util.Optional;
import com.examples2.demo.repository.GananciaDia;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;


public interface StudentService  {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    void eliminar(Long id); // <-- agrega este método

    BigDecimal gananciaDeDia(LocalDate dia);
    List<GananciaDiaDTO> gananciasPorDia(LocalDate desde, LocalDate hasta);
}