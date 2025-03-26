package es.neesis.mvcdemo.servicios;

import es.neesis.mvcdemo.VirtualDBContext.ClienteDBContext;
import es.neesis.mvcdemo.dtos.OutCuentaDTO;
import es.neesis.mvcdemo.modelos.Cliente;
import es.neesis.mvcdemo.modelos.Cuenta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ServicioCuentaImpl implements ServicioCuenta {

    private ClienteDBContext clienteDBContext;
    private Random rnd;

    public ServicioCuentaImpl(ClienteDBContext clienteDBContext) {
        this.clienteDBContext = clienteDBContext;
        rnd = new Random();
    }

    @Override
    public List<Cuenta> getTodasCuentas(Long idCliente) {
        List<Cliente> clientes = clienteDBContext.clientes;
        if (clientes.isEmpty()) {
            throw new RuntimeException("No hay clientes en la base de datos");
        }

        Cliente cliente = clientes.stream()
                .filter(clienteIt -> clienteIt.getId().equals(idCliente)).
                findFirst().
                orElseThrow(() -> new RuntimeException("No se ha encontrado el cliente"));

        return cliente.getCuentas();
    }

    @Override
    public void darAltaCuenta(Long idCliente, Cuenta cuenta) {
        Cliente cliente = clientes.stream()
                .filter(clienteIt -> clienteIt.getId().equals(idCliente)).
                findFirst().
                orElseThrow(() -> new RuntimeException("No se ha encontrado el cliente"));

        String numCuenta = Integer.toString(rnd.nextInt(20));
        cuenta.setNumCuenta(numCuenta);

        cliente.getCuentas().add(cuenta);
    }

    @Override
    public void modificarCuenta(Long idCliente, String numCuenta, Cuenta cuenta) {
        Cliente cliente = clienteDBContext.clientes.stream().
                filter(clienteIt -> clienteIt.getId().equals(idCliente))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el cliente"));

        List<Cuenta> cuentasCliente = cliente.getCuentas();

        if (cuentasCliente.isEmpty()) {
            throw new RuntimeException("No hay cuentas asociadas al cliente");
        }

        Cuenta cuentaToModify = cuentasCliente.stream()
                .filter(cuentaIt -> cuentaIt.getNumCuenta().equals(numCuenta))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hay cuenta con ese número asociada al cliente"));

        cuentaToModify.setBalance(cuenta.getBalance());
        cuentaToModify.setSucursal(cuenta.getSucursal());
    }

    @Override
    public void eliminarCuenta(Long idCliente, String numCuenta) {
        Cliente cliente = clienteDBContext.clientes.stream().
                filter(clienteIt -> clienteIt.getId().equals(idCliente))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el cliente"));

        List<Cuenta> cuentasCliente = cliente.getCuentas();

        if (cuentasCliente.isEmpty()) {
            throw new RuntimeException("No hay cuentas asociadas al cliente");
        }

        Cuenta cuentaToRemove = cuentasCliente.stream()
                .filter(cuenta -> cuenta.getNumCuenta().equals(numCuenta))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hay cuenta con ese número asociada al cliente"));

        cuentasCliente.remove(cuentaToRemove);
    }

    @Override
    public OutCuentaDTO getDetalles(Long idCliente, String numCuenta) {
        Cliente cliente = clienteDBContext.clientes.stream().
                filter(clienteIt -> clienteIt.getId().equals(idCliente))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el cliente"));

        List<Cuenta> cuentasCliente = cliente.getCuentas();

        if (cuentasCliente.isEmpty()) {
            throw new RuntimeException("No hay cuentas asociadas al cliente");
        }

        Cuenta cuenta = cuentasCliente.stream()
                .filter(cuentaIt -> cuentaIt.getNumCuenta().equals(numCuenta))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hay cuenta con ese número asociada al cliente"));

        return new OutCuentaDTO(cuenta.getNumCuenta(), cuenta.getBalance());
    }
}
