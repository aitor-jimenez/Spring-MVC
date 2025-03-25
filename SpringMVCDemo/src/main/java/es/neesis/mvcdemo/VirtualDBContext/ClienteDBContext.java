package es.neesis.mvcdemo.VirtualDBContext;

import es.neesis.mvcdemo.modelos.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteDBContext {

    public List<Cliente> clientes;

    public ClienteDBContext(List<Cliente> clientes) {
        this.clientes = clientes;
    }

}
