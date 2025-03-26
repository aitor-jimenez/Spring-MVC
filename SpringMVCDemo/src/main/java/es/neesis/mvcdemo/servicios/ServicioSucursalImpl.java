package es.neesis.mvcdemo.servicios;

import es.neesis.mvcdemo.modelos.Sucursal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioSucursalImpl implements ServicioSucursal {

    private final List<Sucursal> sucursales = new ArrayList<>();
    private Integer contadorIds = 1;

    @Override
    public List<Sucursal> listarSucursales() {
        return sucursales;
    }

    @Override
    public Sucursal obtenerSucursalPorId(Integer id) {
        return sucursales.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Sucursal crearSucursal(Sucursal sucursal) {
        sucursal.setId(contadorIds++);
        sucursales.add(sucursal);
        return sucursal;
    }

    @Override
    public Sucursal modificarSucursal(Sucursal sucursal) {
        Sucursal sucursalExistente = obtenerSucursalPorId(sucursal.getId());
        if (sucursal == null) {
            return null;
        }

        sucursalExistente.setNombre(sucursal.getNombre());
        sucursalExistente.setDirector(sucursal.getDirector());
        sucursalExistente.setDireccion(sucursal.getDireccion());
        return sucursalExistente;
    }

    @Override
    public boolean borrarSucursal(Integer id) {
        return sucursales.removeIf(s -> s.getId() == id);
    }
}
