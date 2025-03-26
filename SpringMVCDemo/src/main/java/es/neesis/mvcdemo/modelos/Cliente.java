package es.neesis.mvcdemo.modelos;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String dni;
    private String nombre;
    private String direccionPostal;
    private String email;
    private String telefono;
    private Sucursal sucursalPrincipal;
    private List<Cuenta> cuentas;

    public Cliente(String dni, String nombre, String direccionPostal, String email, String telefono, Sucursal sucursalPrincipal) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccionPostal = direccionPostal;
        this.email = email;
        this.telefono = telefono;
        this.sucursalPrincipal = sucursalPrincipal;
        cuentas = new ArrayList<>();
    }

    public String getDni() {
        return this.dni;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDireccionPostal() {
        return this.direccionPostal;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public Sucursal getSucursalPrincipal() {
        return this.sucursalPrincipal;
    }

    public void setSucursalPrincipal(Sucursal sucursal) {
        this.sucursalPrincipal = sucursal;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}
