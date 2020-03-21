package com.uatx.inventarios.services.impl;


import com.uatx.inventarios.dto.ProductoDTO;
import com.uatx.inventarios.model.Producto;
import com.uatx.inventarios.repository.ProductoRepository;
import com.uatx.inventarios.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Long store(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());

        productoRepository.save(producto);
        return producto.getId();
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();

    }

    @Override
    public String listName(ProductoDTO productoDTO){
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        productoRepository.findAll();
        return producto.getNombre();

    }



    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }


}
