package es.neesis.mvcdemo.servicios;

import es.neesis.mvcdemo.modelos.Sucursal;

import java.util.List;

public interface ServicioSucursal {
    List<Sucursal> listarSucursales();

    Sucursal obtenerSucursalPorId(int id);

    Sucursal crearSucursal(Sucursal sucursal);

    Sucursal modificarSucursal(Sucursal sucursal);

    boolean borrarSucursal(int id);
}
