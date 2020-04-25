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

   @GetMapping("/page/nuevo-producto" )
    public String altaProductos(){
        return "nuevo-producto";
    }

    @GetMapping("/page/productos")
    public String redirectConsultaProd() {
        return "consulta-producto";
    }



    // all productos
    @GetMapping("/all")
    @ResponseBody
    public List<ProductoDTO> consultarProductos() {
        return productoService.consultarProductos();
    }


    // all imagen
    @GetMapping("/all/for-table")
    @ResponseBody
    public List<ProductoDTO> findAllWithImg() {
        return productoService.findProdWithImage();
    }



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
    @ResponseBody
    public String findByName(@PathVariable Long productoId) {
        return productoService.eliminar(productoId);
    }



}
