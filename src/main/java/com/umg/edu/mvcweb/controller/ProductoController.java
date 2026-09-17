package com.umg.edu.mvcweb.controller;

import com.umg.edu.mvcweb.dao.ProductoDAO;
import com.umg.edu.mvcweb.modelo.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductoController {

    @Autowired
    private ProductoDAO productoDAO;

    @GetMapping("/productos")
    public String mostrarProductos(Model model) {

        model.addAttribute(
                "producto",
                new Producto()
        );

        model.addAttribute(
                "productos",
                productoDAO.listar()
        );

        return "productos/lista";
    }

    @GetMapping("/productos/nuevo")
    public String nuevoProducto(Model model) {

        model.addAttribute(
                "producto",
                new Producto()
        );

        return "productos/formulario";
    }

    @PostMapping("/productos/guardar")
    public String guardarProducto(
            @ModelAttribute Producto producto) {

        boolean guardado =
                productoDAO.guardar(producto);

        System.out.println(
                "¿Producto guardado? "
                + guardado
        );

        return "redirect:/productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String editarProducto(
            @PathVariable int id,
            Model model) {

        Producto producto =
                productoDAO.buscarPorId(id);

        model.addAttribute(
                "producto",
                producto
        );

        return "productos/formulario";
    }

    @PostMapping("/productos/actualizar")
    public String actualizarProducto(
            @ModelAttribute Producto producto) {

        boolean actualizado =
                productoDAO.actualizar(producto);

        System.out.println(
                "¿Producto actualizado? "
                + actualizado
        );

        return "redirect:/productos";
    }

    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(
            @PathVariable int id) {

        boolean eliminado =
                productoDAO.eliminar(id);

        System.out.println(
                "¿Producto eliminado? "
                + eliminado
        );

        return "redirect:/productos";
    }
}