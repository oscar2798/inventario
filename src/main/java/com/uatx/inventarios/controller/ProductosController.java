package com.uatx.inventarios.controller;

import com.uatx.inventarios.dto.AltaInventarioDTO;
import com.uatx.inventarios.dto.BajaInventarioDTO;
import com.uatx.inventarios.dto.ImagenDTO;
import com.uatx.inventarios.dto.ProductoDTO;
import com.uatx.inventarios.model.AltaInventario;
import com.uatx.inventarios.model.BajaInventario;
import com.uatx.inventarios.model.Producto;
import com.uatx.inventarios.repository.ProductoRepository;
import com.uatx.inventarios.services.InventarioService;
import com.uatx.inventarios.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductosController {
    @Autowired
    private ProductoService productoService;
    @Autowired
    private InventarioService inventarioService;
    @Autowired
    private ProductoRepository productoRepository;

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


    @GetMapping("find/producto/{id}")
    @ResponseBody
    public ProductoDTO windProductoWithIdFech(@PathVariable(value = "id") Long productoId){
        return productoService.findByIdWithProducto(productoId);
    }
    // AltaProductos

    @GetMapping("/buscarAltas/{productoId}")
    @ResponseBody
    public List<AltaInventarioDTO> buscarAltas(@PathVariable(value = "productoId") Long productoId, Map<String, Object> model, RedirectAttributes flash){
        return inventarioService.findAltasByProducto(productoId);
    };

    @PostMapping("/guardarAlta")
    @ResponseBody
    public Long guardarAlta(@RequestBody AltaInventarioDTO altaInventarioDTO) {
        return inventarioService.storeAltaInventario(altaInventarioDTO);
    }



    // Mostrar altas

    @GetMapping("/detalleAltas/{id}")
    public String detalleAlta(@PathVariable(value = "id") String id, Map<String, Object> model, RedirectAttributes flash){

        List<AltaInventarioDTO> altaInventarioDTOS = inventarioService.findAltasByProducto(Long.parseLong(id));

        model.put("DTO",altaInventarioDTOS);

        return "detalle-alta";
    }

    @GetMapping("/alta/{productoId}")
    public String altaById(@PathVariable(value = "productoId")  String productoId, Map<String, Object> model, RedirectAttributes flash){
        ProductoDTO productoDTO = productoService.findByIdWithProducto(Long.parseLong(productoId));
        ImagenDTO imagenDTO = productoDTO.getImagen();
        model.put("producto", productoDTO);
        model.put("base64",imagenDTO.getDataBase64());
        model.put("type",imagenDTO.getMimeType());
        model.put("info","Detalle de altas:");
        model.put("titulo", "Detalle del Producto: " + productoDTO.getNombre());
        return "alta-producto";
    }


    //BajaProductos

    // BajaProductos
    @PostMapping("/guardarBaja")
    @ResponseBody
    public Long guardarBaja(@RequestBody BajaInventarioDTO bajaInventarioDTO) {
        return inventarioService.storeBajaInventario(bajaInventarioDTO);
    }

    // Mostrar bajas


}
