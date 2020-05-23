package com.uatx.inventarios.services.impl;

import com.uatx.inventarios.dto.AltaInventarioDTO;
import com.uatx.inventarios.dto.BajaInventarioDTO;
import com.uatx.inventarios.model.AltaInventario;
import com.uatx.inventarios.model.BajaInventario;
import com.uatx.inventarios.model.Producto;
import com.uatx.inventarios.repository.AltaInventarioRepository;
import com.uatx.inventarios.repository.BajainventarioRepository;
import com.uatx.inventarios.repository.ProductoRepository;
import com.uatx.inventarios.services.InventarioService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
public class InventarioServiceImpl implements InventarioService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InventarioServiceImpl.class);

    @Autowired
    private AltaInventarioRepository altaInventarioRepository;
    @Autowired
    private BajainventarioRepository bajainventarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public Long storeAltaInventario(AltaInventarioDTO altaInventarioDTO){
        AltaInventario altaInventario =modelMapper.map(altaInventarioDTO, AltaInventario.class);
        altaInventario.setFecha(new Date());
        altaInventario.setCantidad(altaInventarioDTO.getCantidad());
        Producto producto = productoRepository.findProductoByIdFetch(altaInventarioDTO.getProducto().getId());
        altaInventario.setProducto(productoRepository.findProductoByIdFetch(producto.getId()));
        producto.setStock((int) (altaInventario.getCantidad()+producto.getStock()));
        altaInventarioRepository.save(altaInventario);
        return altaInventario.getId();
    }

    @Override
    @Transactional
    public Long storeBajaInventario(BajaInventarioDTO bajaInventarioDTO){
        BajaInventario bajaInventario = modelMapper.map(bajaInventarioDTO, BajaInventario.class);
        bajaInventario.setCantidad(bajaInventarioDTO.getCantidad());
        bajaInventario.setFecha(new Date());
        Producto producto = productoRepository.findProductoByIdFetch(bajaInventarioDTO.getProducto().getId());
        bajaInventario.setProducto(productoRepository.findProductoByIdFetch(producto.getId()));
        producto.setStock((int) (producto.getStock()-bajaInventario.getCantidad()));
        bajainventarioRepository.save(bajaInventario);
        return bajaInventario.getId();
    }

    @Override
    public List<AltaInventarioDTO> findAltasByProducto(Long productoId) {

        List<AltaInventario> altaInventario = altaInventarioRepository.findAltaByIdFetchProducto(productoId);
        List<AltaInventarioDTO> altaInventarioDTOS = trasnformToListDTO(altaInventario);
        return altaInventarioDTOS;
    }

    @Override
    public List<BajaInventarioDTO> findBajasByProducto(Long productoId) {

        List<BajaInventario> bajaInventario = bajainventarioRepository.findBajaByIdFetchProducto(productoId);
        List<BajaInventarioDTO> bajaInventarioDTOS = trasnformToListDTOS(bajaInventario);
        return bajaInventarioDTOS;

    }



    /**
     * Metodo de utilidad para pasar los valores de una lista de objetos del tipo
     * Producto a otra lista del tipo ProductoDTO
     * @param altaInventarios
     * @return
     */
    private List<AltaInventarioDTO> trasnformToListDTO(List<AltaInventario> altaInventarios) {
        List<AltaInventarioDTO> altaInventariosDTOS = new ArrayList<>();
        for (AltaInventario altaInventario : altaInventarios) {
            AltaInventarioDTO altaInventarioDTO = modelMapper.map(altaInventario, AltaInventarioDTO.class);
            altaInventariosDTOS.add(altaInventarioDTO);
        }
        return altaInventariosDTOS;
    }

    /**
     * Metodo de utilidad para pasar los valores de una lista de objetos del tipo
     * Producto a otra lista del tipo ProductoDTO
     * @param bajaInventarios
     * @return
     */

    private List<BajaInventarioDTO> trasnformToListDTOS(List<BajaInventario> bajaInventarios) {
        List<BajaInventarioDTO> bajaInventariosDTOS = new ArrayList<>();
        for (BajaInventario bajaInventario : bajaInventarios) {
            BajaInventarioDTO bajaInventarioDTO = modelMapper.map(bajaInventario, BajaInventarioDTO.class);
            bajaInventariosDTOS.add(bajaInventarioDTO);
        }
        return bajaInventariosDTOS;
    }


}
