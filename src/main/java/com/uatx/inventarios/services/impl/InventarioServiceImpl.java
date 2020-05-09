package com.uatx.inventarios.services.impl;

import com.uatx.inventarios.dto.AltaInventarioDTO;
import com.uatx.inventarios.dto.BajaInventarioDTO;
import com.uatx.inventarios.model.AltaInventario;
import com.uatx.inventarios.model.BajaInventario;
import com.uatx.inventarios.repository.AltaInventarioRepository;
import com.uatx.inventarios.repository.BajainventarioRepository;
import com.uatx.inventarios.services.InventarioService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class InventarioServiceImpl implements InventarioService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InventarioServiceImpl.class);

    @Autowired
    private AltaInventarioRepository altaInventarioRepository;
    @Autowired
    private BajainventarioRepository bajainventarioRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Long storeAltaInventario(AltaInventarioDTO altaInventarioDTO){
        AltaInventario altaInventario = modelMapper.map(altaInventarioDTO, AltaInventario.class);
        altaInventario.setFecha(new Date());
        altaInventario.setCantidad((Double) 0D);
        altaInventario.getProducto();
        altaInventarioRepository.save(altaInventario);
        return altaInventario.getId();
    }

    @Override
    public Long storeBajaInventario(BajaInventarioDTO bajaInventarioDTO){

        BajaInventario bajaInventario = modelMapper.map(bajaInventarioDTO, BajaInventario.class);
        bajaInventario.setFecha(new Date());
        bajaInventario.setCantidad((double) 0D);
        bajaInventario.getProducto();
        bajainventarioRepository.save(bajaInventario);
        return bajaInventario.getId();
    }

    @Override
    public List<AltaInventario> findAltasByProducto(Long productoId){
        List<AltaInventario> altaInventarios = altaInventarioRepository.findAllById(Collections.singleton(productoId));
        return altaInventarios;
    }

    @Override
    public List<BajaInventario> findBajasByProducto(Long productoId){
       List<BajaInventario> bajaInventarios = bajainventarioRepository.findAllById(Collections.singleton(productoId));
       return bajaInventarios;
    }



}
