package com.uatx.inventarios.controller;

import com.uatx.inventarios.dto.ProductoDTO;
import com.uatx.inventarios.model.Producto;
import com.uatx.inventarios.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductosController {
    @Autowired
    private ProductoService productoService;

    @PostMapping("/guardar")
    @ResponseBody
    public Long guardarProducto(@RequestBody ProductoDTO productoDTO) {
        return productoService.store(productoDTO);
    }



    @GetMapping("/all")
    @ResponseBody
    public List<ProductoDTO> consultarProductos() {
        return productoService.consultarProductos();
    }

    @GetMapping("/find/by-name")
    @ResponseBody
    public List<ProductoDTO> findByName(@RequestParam String nombre) {

        return productoService.findByName(nombre);
    }



    @DeleteMapping("/eliminar/{productoId}")
    @ResponseBody
    public String findByName (@PathVariable Long productoId){
         return productoService.eliminar(productoId);

    }

    @GetMapping("/page/nuevo-producto" )
    public String altaProductos(){
        return "nuevo-producto";
    }

    @GetMapping("/page/productos")
    public String consultarProd(){
        return "consulta-producto";
    }


}
