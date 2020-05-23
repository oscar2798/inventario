package com.uatx.inventarios.repository;

import com.uatx.inventarios.dto.AltaInventarioDTO;
import com.uatx.inventarios.model.AltaInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AltaInventarioRepository extends JpaRepository<AltaInventario, Long> {
    @Query("SELECT a FROM AltaInventario a JOIN FETCH a.producto where a.producto.id = :productoId")
    List<AltaInventario> findAltaByIdFetchProducto(Long productoId);
}
