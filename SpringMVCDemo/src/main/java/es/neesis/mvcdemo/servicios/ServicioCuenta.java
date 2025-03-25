package es.neesis.mvcdemo.servicios;

import es.neesis.mvcdemo.dtos.OutCuentaDTO;
import es.neesis.mvcdemo.modelos.Cuenta;

import java.util.List;

public interface ServicioCuenta {

    List<Cuenta> getTodasCuentas(Long idCliente);

    void darAltaCuenta(Long idCliente, Cuenta cuenta);

    void modificarCuenta(Long idCliente, String numCuenta, Cuenta cuenta);

    void eliminarCuenta(Long idCliente, String numCuenta);

    OutCuentaDTO getDetalles(Long idCliente, String numCuenta);

}
