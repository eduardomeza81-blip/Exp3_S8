package com.examples2.demo.service;
import com.examples2.demo.repository.GananciaDia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.examples2.demo.Student;
import com.examples2.demo.dto.GananciaDiaDTO;
import com.examples2.demo.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;
import java.time.LocalDate;
  

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {        
        return Optional.ofNullable(studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no encontrado con id: " + id)));
    }
      @Override
    public void eliminar(Long id) {
        try {
            studentRepository.deleteById(id); // intenta borrar directo
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Producto no encontrado: " + id);
        }
    }
    @Override
    public BigDecimal gananciaDeDia(LocalDate dia) {
        return studentRepository.gananciaDeDia(dia);
    }

    @Override
    public List<GananciaDiaDTO> gananciasPorDia(LocalDate desde, LocalDate hasta) {
        return studentRepository.gananciasPorDia(desde, hasta);
    }
}
