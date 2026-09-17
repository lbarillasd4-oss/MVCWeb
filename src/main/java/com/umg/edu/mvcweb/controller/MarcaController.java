package com.umg.edu.mvcweb.controller;

import com.umg.edu.mvcweb.dao.MarcaDAO;
import com.umg.edu.mvcweb.modelo.Marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MarcaController {

    @Autowired
    private MarcaDAO marcaDAO;


    // =====================================================
    // MOSTRAR MARCAS
    // =====================================================

    @GetMapping("/marcas")
    public String mostrarMarcas(Model model) {

        model.addAttribute(
                "marca",
                new Marca()
        );

        model.addAttribute(
                "marcas",
                marcaDAO.listar()
        );

        return "marcas/lista";
    }


    // =====================================================
    // NUEVA MARCA
    // =====================================================

    @GetMapping("/marcas/nuevo")
    public String nuevaMarca(Model model) {

        model.addAttribute(
                "marca",
                new Marca()
        );

        return "marcas/formulario";
    }


    // =====================================================
    // GUARDAR MARCA
    // =====================================================

    @PostMapping("/marcas/guardar")
    public String guardarMarca(
            @ModelAttribute Marca marca) {

        boolean guardado =
                marcaDAO.guardar(marca);

        System.out.println(
                "¿Marca guardada? "
                + guardado
        );

        return "redirect:/marcas";
    }


    // =====================================================
    // EDITAR MARCA
    // =====================================================

    @GetMapping("/marcas/editar/{id}")
    public String editarMarca(
            @PathVariable int id,
            Model model) {

        Marca marca =
                marcaDAO.buscarPorId(id);

        model.addAttribute(
                "marca",
                marca
        );

        return "marcas/formulario";
    }


    // =====================================================
    // ACTUALIZAR MARCA
    // =====================================================

    @PostMapping("/marcas/actualizar")
    public String actualizarMarca(
            @ModelAttribute Marca marca) {

        boolean actualizado =
                marcaDAO.actualizar(marca);

        System.out.println(
                "¿Marca actualizada? "
                + actualizado
        );

        return "redirect:/marcas";
    }


    // =====================================================
    // ELIMINAR MARCA
    // =====================================================

    @GetMapping("/marcas/eliminar/{id}")
    public String eliminarMarca(
            @PathVariable int id) {

        boolean eliminado =
                marcaDAO.eliminar(id);

        System.out.println(
                "¿Marca eliminada? "
                + eliminado
        );

        return "redirect:/marcas";
    }
}
