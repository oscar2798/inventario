package com.uatx.inventarios.repository;

import com.uatx.inventarios.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface ProductoRepository extends JpaRepository<Producto,Long>{

    }

