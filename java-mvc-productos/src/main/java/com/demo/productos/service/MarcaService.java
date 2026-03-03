package com.demo.productos.service;

import com.demo.productos.model.Marca;

import java.util.List;

public interface MarcaService {
    List<Marca> listarTodosLasMarcas();
    Marca obtenerMarcaPorId(Long id);

    Marca guardarMarca(Marca marca);

    void eliminarMarca(Long id);

    List<Marca> buscarMarcasPorNombre(String nombre);

    List<Marca> buscarMarcasPorPrecioMaximo(Double precioMaximo);
}
