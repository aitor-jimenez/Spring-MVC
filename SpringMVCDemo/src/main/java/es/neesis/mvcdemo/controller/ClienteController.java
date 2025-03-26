package es.neesis.mvcdemo.controller;

import es.neesis.mvcdemo.modelos.Cliente;
import es.neesis.mvcdemo.servicios.ServicioCliente;
import es.neesis.mvcdemo.servicios.ServicioSucursal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private ServicioCliente servicioCliente;
    private ServicioSucursal servicioSucursal;

    public ClienteController(ServicioCliente servicioCliente, ServicioSucursal servicioSucursal) {
        this.servicioCliente = servicioCliente;
        this.servicioSucursal = servicioSucursal;
    }

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", this.servicioCliente.listarClientes());
        model.addAttribute("sucursales", this.servicioSucursal.listarSucursales());
        return "/clientes";
    }

    @PostMapping
    public String addCliente(Cliente cliente, Model model) {
        this.servicioCliente.altaCliente(cliente);
        return "/clientes";
    }

    @GetMapping("/{dni}")
    public String getCliente(@RequestParam String dni, Model model) {
        model.addAttribute("cliente", this.servicioCliente.buscarCliente(dni));
        return "detalleCliente";
    }

    @PutMapping("/{dni}")
    public String modificarCliente(@PathVariable String dni, Cliente cliente, Model model) {
        this.servicioCliente.modificarCliente(cliente);
        return "/clientes";
    }

    @PostMapping("/{dni}")
    public String borrarCliente(@RequestParam String dni, Model model) {
        this.servicioCliente.bajaCliente(dni);
        return "/clientes";
    }

}
