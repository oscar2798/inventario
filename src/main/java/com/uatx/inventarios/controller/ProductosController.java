package com.uatx.inventarios.controller;

import com.uatx.inventarios.dto.ProductoDTO;
import com.uatx.inventarios.model.Producto;
import com.uatx.inventarios.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductosController {
    @Autowired
    private ProductoService productoService;

    // Nuevo-producto
    @GetMapping("/page/nuevo-producto")
    public String agregar(Model model){
        model.addAttribute("producto",new ProductoDTO());
        return  "nuevo-producto";
    }

   /* @GetMapping("/page/nuevo-producto" )
    public String altaProductos(){
        return "nuevo-producto";
    }  */



    // all
    @GetMapping("/page/productos")
    public  String listar(Model model){
        List<ProductoDTO>productos=productoService.consultarProductos();
        model.addAttribute("productos", productos);
        return "consulta-producto";

    }


    /*@GetMapping("/page/productos")
    public String consultarProd(){
        return "consulta-producto";
    } */

    @GetMapping("/find/by-name")
    @ResponseBody
    public List<ProductoDTO> findByName(@RequestParam String nombre) {
        return productoService.findByName(nombre);
    }


    // guardar
    @PostMapping("/guardar")
    @ResponseBody
    public Long save(@RequestBody ProductoDTO productoDTO){
          return productoService.store(productoDTO);
    }

    // editar
    @GetMapping("/editar/{productoId}")
    public String editar(@PathVariable Long productoId,Model model){
       Optional<Producto> producto= productoService.editar(productoId);
        model.addAttribute("producto", producto);
        return "nuevo-producto";
    }

    // Eliminar
    @GetMapping("/eliminar/{productoId}")
    public String eliminar(@PathVariable Long productoId,Model model) {
        productoService.eliminar(productoId);
        return "redirect:/productos/page/productos";
    }


    /*@GetMapping("/all")
    @ResponseBody
    public List<ProductoDTO> consultarProductos() {
        return productoService.consultarProductos();
    }

   */



}
