package com.uatx.inventarios.controller;

import com.uatx.inventarios.dto.ProductoDTO;
import com.uatx.inventarios.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
