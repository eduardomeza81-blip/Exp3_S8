package com.examples2.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.examples2.demo.Student;
import com.examples2.demo.dto.GananciaDiaDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;


//public interface StudentRepository  extends JpaRepository<Student, Long> {}
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("""
        select coalesce(sum(cast(s.precio as big_decimal) * cast(s.cantidad as big_decimal)), 0)
        from Student s
        where s.fecha = :dia
    """)
    BigDecimal gananciaDeDia(@Param("dia") LocalDate dia);   // <-- Date

    @Query("""
        select new com.examples2.demo.dto.GananciaDiaDTO(
            s.fecha,
            sum(cast(s.precio as big_decimal) * cast(s.cantidad as big_decimal))
        )
        from Student s
        where s.fecha between :desde and :hasta
        group by s.fecha
        order by s.fecha asc
    """)
    List<GananciaDiaDTO> gananciasPorDia(@Param("desde") LocalDate desde,
                                          @Param("hasta") LocalDate hasta);
}