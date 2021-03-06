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

import javax.transaction.Transactional;
import java.util.*;

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
    @Transactional
    public Long store(ProductoDTO productoDTO) {
        Producto producto = modelMapper.map(productoDTO, Producto.class);
        producto.setStock((int) 0D);
        producto.setFechaAlta(new Date());
        Imagen imagen = modelMapper.map(productoDTO.getImagen(),Imagen.class);
        imagenRepository.save(imagen);
        /*if (imagen.getDataBase64().length() > 100){
            throw  new BusinessException("No es posible guardar una imagen grande");

        }*/
        producto.setImagen(imagen);

        productoRepository.save(producto);

        return producto.getId();
    }

    @Override
    public List<ProductoDTO> consultarProductos() {
        return trasnformToListDTO(productoRepository.findAll());

    }



    @Override
    public Optional<Producto> editar(Long  productoId){
        Optional<Producto> producto = productoRepository.findById(productoId);
        return producto;
    }



    @Override
    public String eliminar(Long productoId) {
        try {
            Producto producto = productoRepository.findById(productoId).orElse(null);
            if (producto == null) {
                throw new BusinessException("No existe un producto para el id " + productoId);
            }
            Imagen imagen = producto.getImagen();
            productoRepository.delete(producto);
            imagenRepository.delete(imagen);
            return "Producto eliminado exitosamente";
        } catch (Exception e) {
            LOGGER.error("Error al eliminar producto con id {}", productoId);
            LOGGER.error("Exception: ", e);
            if(e instanceof BusinessException){
                return e.getMessage();
            } else {
                return "Error no controlado: " + e.getMessage();
            }

        }
    }

    @Override
    public List<ProductoDTO> findProdWithImage() {
        List<Producto> productos = productoRepository.findProductosFetchImagen();
        List<ProductoDTO> produtosDTO = trasnformToListDTO(productos);
        return produtosDTO;
    }

    @Override
    public ProductoDTO findByIdWithProducto(Long productoId) {
        Producto producto = productoRepository.findProductoByIdFetch(productoId);
        return trasnformToDTO(producto);

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

    private ProductoDTO trasnformToDTO(Producto producto) {
        ProductoDTO productoDTO = modelMapper.map(producto, ProductoDTO.class);
        return productoDTO;
    }

}
