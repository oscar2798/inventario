package com.uatx.inventarios.services;

import com.uatx.inventarios.dto.AltaInventarioDTO;
import com.uatx.inventarios.dto.BajaInventarioDTO;
import com.uatx.inventarios.model.BajaInventario;

import java.util.List;
import java.util.Optional;

public interface InventarioService {
    Long storeAltaInventario(AltaInventarioDTO altaInventarioDTO);
    Long storeBajaInventario(BajaInventarioDTO bajaInventarioDTO);

    List<AltaInventarioDTO> findAltasByProducto(Long productoId);
    List<BajaInventarioDTO> findBajasByProducto(Long productoId);


}
