package com.demo.productos.service;

import com.demo.productos.model.Marca;
import com.demo.productos.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MarcasServicelmpl implements MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public List<Marca> listarTodosLasMarcas() {
        return marcaRepository.findAll();
    }

    @Override
    public Marca obtenerMarcaPorId(Long id) {
        return marcaRepository.findById(id).orElseThrow(()-> new RuntimeException("Marca no encontrada con el ID : " + id));
    }

    @Override
    @Transactional
    public Marca guardarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    @Override
    public void eliminarMarca(Long id) {
        marcaRepository.deleteById(id);

    }

    @Override

    public List<Marca> buscarMarcasPorNombre(String nombre) {
        return marcaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Marca> buscarMarcasPorPrecioMaximo(Double precioMaximo) {
        return marcaRepository.buscarMarcasPorPrecioMaximo(precioMaximo);
    }
}
