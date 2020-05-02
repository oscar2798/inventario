package com.uatx.inventarios.services.impl;

import com.uatx.inventarios.dto.AltaInventarioDTO;
import com.uatx.inventarios.dto.BajaInventarioDTO;
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
        return null;
    }

    @Override
    public Long storeBajaInventario(BajaInventarioDTO bajaInventarioDTO){
        return null;
    }

    @Override
    public List<AltaInventarioDTO> findAltasByProducto(Long productoId){
        return null;
    }

    @Override
    public List<BajaInventarioDTO> findBajasByProducto(Long productoId){
        return null;
    }



}
