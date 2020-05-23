package com.uatx.inventarios.model;

import com.uatx.inventarios.dto.AltaInventarioDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "alta_inventario")
@SequenceGenerator(name = "alta_seq")
public class AltaInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "alta_seq")
    private Long id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "cantidad")
    private Double cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private Producto producto;


    public Long getId() {
        return id;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }


}
