package com.uatx.inventarios.model;

import javax.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

    //Generar ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "nombre", length = 50)
    private  String nombre;


    public Long getId() {
        return id;
    }


    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
