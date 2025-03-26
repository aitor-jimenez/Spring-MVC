package es.neesis.mvcdemo.controller;

import es.neesis.mvcdemo.VirtualDBContext.ClienteDBContext;
import es.neesis.mvcdemo.dtos.OutCuentaDTO;
import es.neesis.mvcdemo.modelos.Cuenta;
import es.neesis.mvcdemo.servicios.ServicioCuenta;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cuentas")
public class CuentaController {

    private ServicioCuenta servicioCuenta;
    private ClienteDBContext clienteDBContext;

    public CuentaController(ServicioCuenta servicioCuenta, ClienteDBContext clienteDBContext) {
        this.servicioCuenta = servicioCuenta;
        this.clienteDBContext = clienteDBContext;
    }

    @GetMapping("/{idCliente}/all")
    public String listarCuentas(@PathVariable Long idCliente, Model model) {
        List<Cuenta> cuentas;
        try {
            cuentas = servicioCuenta.getTodasCuentas(idCliente);
        } catch (Exception e) {
            cuentas = new ArrayList<>();
        }

        model.addAttribute("listaCuentas", cuentas);

        return "cuentas";
    }

    @PostMapping("/{idCliente}/")
    public String darAltaCuenta(@PathVariable Long idCliente, @RequestBody Cuenta cuenta, Model model) {
        try {
            servicioCuenta.darAltaCuenta(idCliente, cuenta);
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "redirect:/cuentas?error=notFound";
        }
        return "redirect:/cuentas" + +idCliente;
    }

    @PutMapping("/{idCliente}/{numCuenta}")
    public String modificarCuenta(@PathVariable Long idCliente, @PathVariable String numCuenta, @RequestBody Cuenta cuenta, Model model) {
        try {
            servicioCuenta.modificarCuenta(idCliente, numCuenta, cuenta);
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "redirect:/cuentas?error=notFound";
        }

        return "redirect:/cuentas/" + idCliente;
    }

    @GetMapping("/{idCliente}/{numCuenta}/delete")
    public String eliminarCuenta(@PathVariable Long idCliente, @PathVariable String numCuenta, Model model) {
        try {
            servicioCuenta.eliminarCuenta(idCliente, numCuenta);
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "redirect:/cuentas?error=notFound";
        }
        return "redirect:/cuentas/" + idCliente;
    }

    @GetMapping("/{idCliente}/{numCuenta}")
    public String obtenerDetalles(@PathVariable Long idCliente, @PathVariable String numCuenta, Model model) {
        OutCuentaDTO cuenta;
        try {
            cuenta = servicioCuenta.getDetalles(idCliente, numCuenta);
            model.addAttribute("detallesCuenta", cuenta);
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "redirect:/cuentas?error=notFound";
        }
        return "detallesCuenta";
    }
}
