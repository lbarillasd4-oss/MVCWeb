package com.umg.edu.mvcweb.controller;

import com.umg.edu.mvcweb.dao.ClienteDAO;
import com.umg.edu.mvcweb.modelo.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClienteController {

    @Autowired
    private ClienteDAO clienteDAO;


    // =====================================================
    // MOSTRAR CLIENTES
    // =====================================================
    @GetMapping("/clientes")
    public String mostrarClientes(Model model) {

        model.addAttribute(
                "cliente",
                new Cliente()
        );

        model.addAttribute(
                "clientes",
                clienteDAO.listar()
        );

        return "clientes/lista";
    }


    // =====================================================
    // NUEVO CLIENTE
    // =====================================================
    @GetMapping("/clientes/nuevo")
    public String nuevoCliente(Model model) {

        model.addAttribute(
                "cliente",
                new Cliente()
        );

        return "clientes/formulario";
    }


    // =====================================================
    // GUARDAR CLIENTE
    // =====================================================
    @PostMapping("/clientes/guardar")
    public String guardarCliente(
            @ModelAttribute Cliente cliente) {

        boolean guardado =
                clienteDAO.guardar(cliente);

        System.out.println(
                "¿Cliente guardado? "
                + guardado
        );

        return "redirect:/clientes";
    }


    // =====================================================
    // EDITAR CLIENTE
    // =====================================================
    @GetMapping("/clientes/editar/{id}")
    public String editarCliente(
            @PathVariable int id,
            Model model) {

        Cliente cliente =
                clienteDAO.buscarPorId(id);

        model.addAttribute(
                "cliente",
                cliente
        );

        return "clientes/formulario";
    }


    // =====================================================
    // ACTUALIZAR CLIENTE
    // =====================================================
    @PostMapping("/clientes/actualizar")
    public String actualizarCliente(
            @ModelAttribute Cliente cliente) {

        boolean actualizado =
                clienteDAO.actualizar(cliente);

        System.out.println(
                "¿Cliente actualizado? "
                + actualizado
        );

        return "redirect:/clientes";
    }


    // =====================================================
    // ELIMINAR CLIENTE
    // =====================================================
    @GetMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(
            @PathVariable int id) {

        boolean eliminado =
                clienteDAO.eliminar(id);

        System.out.println(
                "¿Cliente eliminado? "
                + eliminado
        );

        return "redirect:/clientes";
    }
}
