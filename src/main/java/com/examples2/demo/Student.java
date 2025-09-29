package com.examples2.demo;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import jakarta.persistence.*;
import javax.xml.crypto.Data;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ventas")
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="nombre")
    private String nombre;
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "precio", nullable = false, precision = 19, scale = 2)
    private BigDecimal precio;
    @Column(name = "cantidad")
    private Long cantidad;
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    //Getters y stters
    public Long getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getCategoria(){
        return categoria;
    }
    public Number getPrecio(){
        return precio;
    }
    public Long getCantidad(){
        return cantidad;
    }
    public LocalDate getFecha(){
        return fecha;
    }
    public void setId(Long id){
        this.id=id;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setCategoria(String categoria){
        this.categoria=categoria;
    }
    public void setPrecio(BigDecimal precio){
        this.precio=precio;
    }
    public void setCantidad(Long cantidad){
        this.cantidad=cantidad;
    }
    public void setFecha(LocalDate fecha){
        this.fecha=fecha;
    }
     // utilidad: subtotal
    public BigDecimal getSubtotal() {
        return precio.multiply(BigDecimal.valueOf(cantidad));
    }
}