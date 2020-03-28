package com.uatx.inventarios.services;

import com.uatx.inventarios.dto.ProductoDTO;
import com.uatx.inventarios.model.Producto;

import java.util.List;

public interface ProductoService {

    Long store (ProductoDTO productoDTO);

    List<ProductoDTO> consultarProductos();

    List<ProductoDTO> findByName(String nombre);

    String  eliminar(Long productoId);

    List<ProductoDTO> findProdWithImage();
}
