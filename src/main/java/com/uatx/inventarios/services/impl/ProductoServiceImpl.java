package com.uatx.inventarios.services.impl;


import com.uatx.inventarios.dto.ProductoDTO;
import com.uatx.inventarios.exceptions.BusinessException;
import com.uatx.inventarios.model.Imagen;
import com.uatx.inventarios.model.Producto;
import com.uatx.inventarios.repository.ImagenRepository;
import com.uatx.inventarios.repository.ProductoRepository;
import com.uatx.inventarios.services.ProductoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductoServiceImpl.class);

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public Long store(ProductoDTO productoDTO) {
        Producto producto = modelMapper.map(productoDTO,Producto.class);
        producto.setStock(0D);
        producto.setFechaAlta(new Date());
        Imagen imagen = modelMapper.map(productoDTO.getImagen(),Imagen.class);

        imagenRepository.save(imagen);
        producto.setImagen(imagen);
        productoRepository.save(producto);
        return producto.getId();
    }

    @Override
    public List<ProductoDTO> consultarProductos() {
        return trasnformToListDTO(productoRepository.findAll());

    }

    /**
     * Busca todos los productos que en el nombre contengar el valor del parametro nombre
     * ej. nombre = refres, resultado: refresco de cola, refresco de naranja
     * @param  nombre
     * @return productos cuyo nombre coincida con el parametro
     */

    @Override
    public List<ProductoDTO> findByName(String nombre){
        return trasnformToListDTO(productoRepository.findProductosNombreContaining(nombre));

    }


    @Override
    public String eliminar(Long productoId) {
        try {
            Producto producto = productoRepository.findById(productoId).orElse(null);
            if (producto == null) {
                throw new BusinessException("No existe un producto para el id " + productoId);
            }
            productoRepository.delete(producto);
            return "Producto eliminado exitosamente";
        } catch (Exception e) {
            LOGGER.error("Error al eliminar producto con id {}", productoId);
            LOGGER.error("Exception: ", e);
            if(e instanceof BusinessException){
                return e.getMessage();
            }else{
                return "Error no controlado: "+ e.getMessage();
            }

        }
    }

    @Override
    public List<ProductoDTO> findProdWithImage(){
        List<Producto> productos = productoRepository.findProductosFetchImagen();
        List<ProductoDTO> productosDTO = trasnformToListDTO(productos);
        return productosDTO;

    }

    /**
     * Metodo de utilidad para pasar los valores de una lista de objetos del tipo
     * Producto a otra lista del tipo ProductoDTO
     * @param productos
     * @return
     */

    private List<ProductoDTO> trasnformToListDTO(List<Producto> productos) {
        List<ProductoDTO> productoDTOS = new ArrayList<>();
        for (Producto producto : productos) {
            ProductoDTO productoDTO = modelMapper.map(producto, ProductoDTO.class);
            productoDTOS.add(productoDTO);
        }
        return productoDTOS;
    }
}
