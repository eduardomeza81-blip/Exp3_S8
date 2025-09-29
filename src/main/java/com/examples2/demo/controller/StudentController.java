package com.examples2.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.examples2.demo.dto.GananciaDiaDTO;
import com.examples2.demo.Student;
import com.examples2.demo.service.StudentService;
import com.examples2.demo.repository.GananciaDia;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;

// 👇 MUY IMPORTANTE: imports estáticos
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.xml.crypto.Data;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
     // DELETE: "eliminar"
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable long id) {
        studentService.eliminar(id);
    }
    @GetMapping("/ganancias/dia")
    public EntityModel<Map<String, Object>> gananciaDia(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
    BigDecimal total = studentService.gananciaDeDia(fecha);
    Map<String, Object> body = Map.of("fecha", fecha.toString(), "total", total);
        return EntityModel.of(body,
        linkTo(methodOn(StudentController.class).gananciaDia(fecha)).withSelfRel(),
        linkTo(methodOn(StudentController.class).gananciasDiarias(fecha.withDayOfMonth(1), fecha.withDayOfMonth(fecha.lengthOfMonth()))).withRel("ganancias-diarias")
        );
    }

    @GetMapping("/ganancias/diarias")
    public CollectionModel<EntityModel<GananciaDiaDTO>> gananciasDiarias(
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        List<GananciaDiaDTO> data = studentService.gananciasPorDia(desde, hasta);
        List<EntityModel<GananciaDiaDTO>> items = data.stream().map(d -> {
        EntityModel<GananciaDiaDTO> model = EntityModel.of(d);
            LocalDate fecha = d.dia(); 
            model.add(linkTo(methodOn(StudentController.class).gananciaDia(fecha)).withRel("detalles-dia"));
            return model;
        }
        ).collect(Collectors.toList());
        return CollectionModel.of(items,linkTo(methodOn(StudentController.class).gananciasDiarias(desde, hasta)).withSelfRel());
        }
}
