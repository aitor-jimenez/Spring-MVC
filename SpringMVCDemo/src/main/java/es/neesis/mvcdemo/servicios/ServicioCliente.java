package es.neesis.mvcdemo.servicios;

import es.neesis.mvcdemo.modelos.Cliente;

import java.util.List;

public interface ServicioCliente {
    void altaCliente(Cliente cliente);
    void bajaCliente(String dni);
    void modificarCliente(Cliente cliente);
    List<Cliente> listarClientes();
    Cliente buscarCliente(String dni);
}
