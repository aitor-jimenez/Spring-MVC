package es.neesis.mvcdemo.servicios;

import es.neesis.mvcdemo.modelos.Sucursal;

import java.util.List;

public interface ServicioSucursal {
    List<Sucursal> listarSucursales();

    Sucursal obtenerSucursalPorId(Integer id);

    Sucursal crearSucursal(Sucursal sucursal);

    Sucursal modificarSucursal(Sucursal sucursal);

    boolean borrarSucursal(Integer id);
}
