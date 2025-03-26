package es.neesis.mvcdemo.servicios;

import es.neesis.mvcdemo.modelos.Cliente;
import es.neesis.mvcdemo.modelos.Cuenta;
import es.neesis.mvcdemo.modelos.Sucursal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ServicioCuentaImpl implements ServicioCuenta {

    private ServicioCliente servicioCliente;
    private ServicioSucursal servicioSucursal;
    private List<Cuenta> cuentas;
    private Random rnd;

    public ServicioCuentaImpl(ServicioCliente servicioCliente, ServicioSucursal servicioSucursal) {
        this.servicioCliente = servicioCliente;
        this.servicioSucursal = servicioSucursal;
        this.cuentas = new ArrayList<>();
        rnd = new Random();
    }

    @Override
    public List<Cuenta> getTodasCuentas() {
        return cuentas;
    }

    @Override
    public List<Cuenta> getTodasCuentasCliente(String idCliente) {
        List<Cliente> clientes = servicioCliente.listarClientes();
        if (clientes.isEmpty()) {
            throw new RuntimeException("No hay clientes en la base de datos");
        }

        Cliente cliente = clientes.stream()
                .filter(clienteIt -> clienteIt.getDni().equals(idCliente)).
                findFirst().
                orElseThrow(() -> new RuntimeException("No se ha encontrado el cliente"));

        return cliente.getCuentas();
    }

    @Override
    public void darAltaCuenta(Cuenta cuenta) {
        Sucursal sucursal = servicioSucursal.obtenerSucursalPorId(cuenta.getSucursal().getId());
        cuenta.setSucursal(sucursal);

        String numCuenta = Integer.toString(rnd.nextInt(20));
        cuenta.setNumCuenta(numCuenta);

        cuentas.add(cuenta);
    }

    @Override
    public void modificarCuenta(String numCuenta, Cuenta cuenta) {
        Cuenta cuentaToModify = cuentas.stream()
                .filter(c -> c.getNumCuenta().equals(numCuenta))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hay cuenta con ese número asociada al cliente"));

        Sucursal sucursal = servicioSucursal.obtenerSucursalPorId(cuenta.getSucursal().getId());

        cuentaToModify.setNumCuenta(cuenta.getNumCuenta());
        cuentaToModify.setSucursal(cuenta.getSucursal());
        cuentaToModify.setBalance(cuenta.getBalance());
        cuentaToModify.setSucursal(cuenta.getSucursal());
    }

    @Override
    public void eliminarCuenta(String numCuenta) {
        cuentas.removeIf(c -> c.getNumCuenta().equals(numCuenta));
    }

    @Override
    public Cuenta getDetalles(String numCuenta) {
        Cuenta cuenta = cuentas.stream()
                .filter(cuentaIt -> cuentaIt.getNumCuenta().equals(numCuenta))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hay cuenta con ese número asociada al cliente"));

        return cuenta;
    }
}
