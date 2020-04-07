package com.uatx.inventarios.controller;

import com.uatx.inventarios.dto.ProductoDTO;
import com.uatx.inventarios.model.Producto;
import com.uatx.inventarios.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductosController {
    @Autowired
    private ProductoService productoService;

    // Nuevo

    @GetMapping("/page/nuevo")
    public String agregar(Model model){
        model.addAttribute("producto",new ProductoDTO());
        return  "nuevo-producto";
    }



    // all
    @GetMapping("/page/productos")
    public  String listar(Model model){
        List<ProductoDTO>productos=productoService.consultarProductos();
        model.addAttribute("productos", productos);
        return "consulta-producto";

    }

    // guardar
    @PostMapping("/guardar")
    public  String save(ProductoDTO productoDTO, Model model){
        productoService.store(productoDTO);
        return "redirect:/productos/page/productos";

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


   /* @PostMapping("/guardar")
    @ResponseBody
    public Long guardarProducto(@RequestBody ProductoDTO productoDTO){
        return productoService.store(productoDTO);
    }


    public Long guardarProducto(@RequestBody ProductoDTO productoDTO) {
        return productoService.store(productoDTO);
    } */



    /*@GetMapping("/all")
    @ResponseBody
    public List<ProductoDTO> consultarProductos() {
        return productoService.consultarProductos();
    }



    @GetMapping("/find/by-name")
    @ResponseBody
    public List<ProductoDTO> findByName(@RequestParam String nombre) {

        return productoService.findByName(nombre);
    }

   */

    /*@DeleteMapping("/eliminar/{productoId}")
    @ResponseBody
    public String findByName (@PathVariable Long productoId){
         return productoService.eliminar(productoId);

    } */

   /* @GetMapping("/page/nuevo-producto" )
    public String altaProductos(){
        return "nuevo-producto";
    }

    @GetMapping("/page/productos")
    public String consultarProd(){
        return "consulta-producto";
    } */

}
