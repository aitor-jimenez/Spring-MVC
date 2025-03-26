package es.neesis.mvcdemo.servicios;

import es.neesis.mvcdemo.modelos.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServicioClienteImpl implements ServicioCliente {

    private Map<String, Cliente> listaClientes;

    public ServicioClienteImpl() {
        this.listaClientes = new HashMap<>();
    }

    public void altaCliente(Cliente cliente) {
        this.listaClientes.put(cliente.getDni(), cliente);
    }

    public void bajaCliente(String dni) {
        // Borrar cuentas asociadas
        this.listaClientes.remove(dni);
    }

    public void modificarCliente(Cliente cliente) {
        // Modificar cuentas asociadas
        this.listaClientes.replace(cliente.getDni(), cliente);
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();

        for (Map.Entry<String, Cliente> cliente : this.listaClientes.entrySet()) {
            clientes.add(cliente.getValue());
        }

        return clientes;
    }

    public Cliente buscarCliente(String dni) {
        return this.listaClientes.get(dni);
    }

}
