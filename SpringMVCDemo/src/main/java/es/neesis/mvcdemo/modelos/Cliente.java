package es.neesis.mvcdemo.modelos;

import org.springframework.stereotype.Component;

public class Cliente {

    private String dni;
    private String nombre;
    private String direccion_postal;
    private String email;
    private String telefono;
    private Sucursal sucursal_principal;

    public Cliente(String dni, String nombre, String direccion_postal, String email, String telefono, Sucursal sucursal_principal) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion_postal = direccion_postal;
        this.email = email;
        this.telefono = telefono;
        this.sucursal_principal = sucursal_principal;
    }

    public String getDni() {
        return this.dni;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDireccion_postal() {
        return this.direccion_postal;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public Sucursal getSucursal_principal() {
        return this.sucursal_principal;
    }

}
