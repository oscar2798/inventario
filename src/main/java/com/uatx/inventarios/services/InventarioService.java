package com.uatx.inventarios.services;

import com.uatx.inventarios.dto.AltaInventarioDTO;
import com.uatx.inventarios.dto.BajaInventarioDTO;
import com.uatx.inventarios.model.AltaInventario;
import com.uatx.inventarios.model.BajaInventario;

import java.util.List;

public interface InventarioService {
    Long storeAltaInventario(AltaInventarioDTO altaInventarioDTO);
    Long storeBajaInventario(BajaInventarioDTO bajaInventarioDTO);

    List<AltaInventario> findAltasByProducto(Long productoId);
    List<BajaInventario> findBajasByProducto(Long productoId);


}
