package com.demo.productos.controller;

import com.demo.productos.model.Marca;
import com.demo.productos.model.Producto;
import com.demo.productos.service.MarcaService;
import com.demo.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/marcas")
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    // Mostrar todos los productos
    @GetMapping({"", "/"})
    public String listarmarcas(Model model) {
        List<Marca> marcas = marcaService.listarTodosLasMarcas();
        System.out.println("lISTA  " + marcas);
        model.addAttribute("marcas", marcas);
        return "listar-marcas";
    }

    // Formulario para crear un nuevo marca
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoMarca(Model model) {
        model.addAttribute("marca", new Marca());
        return "crear-marca";
    }

    // Guardar un nuevo marca
    @PostMapping("/guardar")
    public String guardarMarca(@Valid @ModelAttribute("marca") Marca marca,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "crear-marca";
        }

        marcaService.guardarMarca(marca);
        redirectAttributes.addFlashAttribute("mensaje", "Marca guardado con éxito.");
        return "redirect:/marcas";
    }

    // Formulario para editar un producto existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarMarca(@PathVariable Long id, Model model) {
        Marca marca = marcaService.obtenerMarcaPorId(id);
        model.addAttribute("marca", marca);
        return "editar-marca";
    }

    // Actualizar un producto existente
    @PostMapping("/actualizar/{id}")
    public String actualizarMarca(@PathVariable Long id,
                                  @Valid @ModelAttribute("marca") Marca marca,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "editar-marca";
        }

        marca.setId(id);
        marcaService.guardarMarca(marca);
        redirectAttributes.addFlashAttribute("mensaje", "Marca actualizado con éxito.");
        return "redirect:/marcas";
    }

    // Eliminar un marca
    @GetMapping("/eliminar/{id}")
    public String eliminarMarca(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        marcaService.eliminarMarca(id);
        redirectAttributes.addFlashAttribute("mensaje", "Marca eliminado con éxito.");
        return "redirect:/marcas";
    }

    // Buscar marcas por nombre
    @GetMapping("/buscar")
    public String buscarmarcas(@RequestParam(required = false) String nombre, Model model) {
        List<Marca> marcas;

        if (nombre != null && !nombre.isEmpty()) {
            marcas = marcaService.buscarMarcasPorNombre(nombre);
            model.addAttribute("nombreBusqueda", nombre);
        } else {
            marcas = marcaService.listarTodosLasMarcas();
        }

        model.addAttribute("marcas", marcas);
        return "listar-marcas";
    }


}
