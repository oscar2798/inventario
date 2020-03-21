package com.uatx.inventarios.services;

import com.uatx.inventarios.dto.ProductoDTO;
import com.uatx.inventarios.model.Producto;

import java.util.List;

public interface ProductoService {

    Long store (ProductoDTO productoDTO);

    List<Producto> listarProductos();

    String listName (ProductoDTO productoDTO);

    void  eliminar(Long id);
}
