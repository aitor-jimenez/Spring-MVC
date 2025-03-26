package es.neesis.mvcdemo.servicios;

import es.neesis.mvcdemo.modelos.Sucursal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioSucursalImpl implements ServicioSucursal {

    private final List<Sucursal> sucursales = new ArrayList<>();
    private Integer contadorIds = 1;

    public ServicioSucursalImpl() {
        this.crearSucursal(new Sucursal(1, "Sucursal 1", "Director 1", "Dirección 1"));
        this.crearSucursal(new Sucursal(2, "Sucursal 2", "Director 2", "Dirección 2"));
        this.crearSucursal(new Sucursal(3, "Sucursal 3", "Director 3", "Dirección 3"));
    }

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
