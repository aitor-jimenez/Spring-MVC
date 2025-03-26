package es.neesis.mvcdemo.controller;

import es.neesis.mvcdemo.modelos.Cliente;
import es.neesis.mvcdemo.modelos.Cuenta;
import es.neesis.mvcdemo.modelos.Sucursal;
import es.neesis.mvcdemo.servicios.ServicioCliente;
import es.neesis.mvcdemo.servicios.ServicioCuenta;
import es.neesis.mvcdemo.servicios.ServicioSucursal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cuentas")
public class CuentaController {

    private ServicioCuenta servicioCuenta;
    private ServicioSucursal servicioSucursal;
    private ServicioCliente servicioCliente;

    public CuentaController(ServicioCuenta servicioCuenta, ServicioSucursal servicioSucursal, ServicioCliente servicioCliente) {
        this.servicioCuenta = servicioCuenta;
        this.servicioSucursal = servicioSucursal;
        this.servicioCliente = servicioCliente;
    }

    @GetMapping()
    public String allCuentas(Model model) {
        List<Cuenta> cuentas;
        try {
            cuentas = servicioCuenta.getTodasCuentas();
        } catch (Exception e) {
            cuentas = new ArrayList<>();
        }

        List<Sucursal> sucursales = servicioSucursal.listarSucursales();
        List<Cliente> clientes = servicioCliente.listarClientes();

        model.addAttribute("clientes", clientes);
        model.addAttribute("sucursales", sucursales);
        model.addAttribute("listaCuentas", cuentas);

        return "cuentas";
    }

    @GetMapping("/{idCliente}/all")
    public String listarCuentas(@PathVariable String idCliente, Model model) {
        List<Cuenta> cuentas;
        try {
            cuentas = servicioCuenta.getTodasCuentasCliente(idCliente);
        } catch (Exception e) {
            cuentas = new ArrayList<>();
        }

        model.addAttribute("listaCuentas", cuentas);

        return "cuentas";
    }

    @GetMapping("/{numCuenta}")
    public String obtenerDetalles(@PathVariable String numCuenta, Model model) {
        Cuenta cuenta;
        try {
            cuenta = servicioCuenta.getDetalles(numCuenta);
            model.addAttribute("detallesCuenta", cuenta);
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "redirect:/cuentas?error=notFound";
        }
        return "detallesCuenta";
    }

    @PostMapping()
    public String darAltaCuenta(Cuenta cuenta, Model model) {
        try {
            servicioCuenta.darAltaCuenta(cuenta);
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "redirect:/cuentas?error=notFound";
        }

        return "redirect:/cuentas";
    }

    @GetMapping("/{numCuenta}/edit")
    public String formularioModificarCuenta(@PathVariable String numCuenta, Model model) {
        model.addAttribute("cuenta", servicioCuenta.getDetalles(numCuenta));
        return "editarCuenta";
    }

    @PostMapping("/{numCuenta}")
    public String modificarCuenta(@PathVariable String numCuenta, Cuenta cuenta, Model model) {
        try {
            servicioCuenta.modificarCuenta(numCuenta, cuenta);
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "redirect:/cuentas?error=notFound";
        }

        return "redirect:/cuentas";
    }

    @GetMapping("/{numCuenta}/delete")
    public String eliminarCuenta(@PathVariable String numCuenta, Model model) {
        try {
            servicioCuenta.eliminarCuenta(numCuenta);
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "redirect:/cuentas?error=notFound";
        }
        return "redirect:/cuentas";
    }
}
