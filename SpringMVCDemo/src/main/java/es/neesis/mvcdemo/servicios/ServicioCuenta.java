package es.neesis.mvcdemo.servicios;

import es.neesis.mvcdemo.modelos.Cuenta;

import java.util.List;

public interface ServicioCuenta {

    List<Cuenta> getTodasCuentas();

    List<Cuenta> getTodasCuentasCliente(String idCliente);

    void darAltaCuenta(Cuenta cuenta);

    void modificarCuenta(String numCuenta, Cuenta cuenta);

    void eliminarCuenta(String numCuenta);

    Cuenta getDetalles(String numCuenta);

}
