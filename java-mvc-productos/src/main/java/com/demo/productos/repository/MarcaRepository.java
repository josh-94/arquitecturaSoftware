package com.demo.productos.repository;

import com.demo.productos.model.Marca;
import com.demo.productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository <Marca, Long> {
    List<Marca> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT p FROM Marca p WHERE p.precio <= ?1")
    List<Marca> buscarMarcasPorPrecioMaximo(Double precioMaximo);
}


