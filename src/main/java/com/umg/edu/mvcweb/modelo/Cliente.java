package com.umg.edu.mvcweb.modelo;

public class Cliente {

    private int idCliente;
    private String nit;
    private String nombre;
    private String apellidos;
    private String email;

    public Cliente() {
    }

    public Cliente(int idCliente, String nit, String nombre,
                   String apellidos, String email) {

        this.idCliente = idCliente;
        this.nit = nit;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}