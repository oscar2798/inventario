package com.uatx.inventarios.services;

import com.uatx.inventarios.dto.ProductoDTO;
import com.uatx.inventarios.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Long store (ProductoDTO productoDTO);

    List<ProductoDTO> consultarProductos();

    List<ProductoDTO> findByName(String nombre);

    Optional<Producto> editar(Long productoId);

    String  eliminar(Long productoId);

    List<ProductoDTO> findProdWithImage();

}
