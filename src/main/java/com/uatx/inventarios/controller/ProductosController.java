package com.uatx.inventarios.controller;

import com.uatx.inventarios.dto.ProductoDTO;
import com.uatx.inventarios.model.Producto;
import com.uatx.inventarios.services.ProductoService;
import com.uatx.inventarios.services.impl.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductosController {
    @Autowired
    private ProductoService productoService;

    @GetMapping("/guardar")
    @ResponseBody
    public Long guardarProducto(@RequestParam String nombre){
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre(nombre);
        return productoService.store(productoDTO);
    }



    @GetMapping("/listar")
    public List<Producto> listarProductos(){
        return productoService.listarProductos();
    }



    @DeleteMapping("/guardar/{id}")
    public void eliminar(@PathVariable Long id){
        productoService.eliminar(id);

    }


}
