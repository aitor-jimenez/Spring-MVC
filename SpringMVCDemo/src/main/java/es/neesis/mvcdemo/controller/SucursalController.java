package es.neesis.mvcdemo.controller;

import es.neesis.mvcdemo.modelos.Sucursal;
import es.neesis.mvcdemo.servicios.ServicioSucursal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sucursales")
public class SucursalController {

    private final ServicioSucursal servicioSucursal;

    public SucursalController(ServicioSucursal servicioSucursal) {
        this.servicioSucursal = servicioSucursal;
    }

    @GetMapping
    public String listarSucursales(Model model) {
        List<Sucursal> sucursales = servicioSucursal.listarSucursales();
        model.addAttribute("sucursales", sucursales);
        return "sucursales";
    }

    @GetMapping("/{id}")
    public String mostrarFormulariDetalle(@PathVariable int id, Model model) {
        Sucursal sucursal = servicioSucursal.obtenerSucursalPorId(id);
        if (sucursal == null) {
            return "redirect:/sucursales?error=notfound";
        }
        model.addAttribute("sucursal", sucursal);
        return "detalleSucursal";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
        Sucursal sucursal = servicioSucursal.obtenerSucursalPorId(id);
        if (sucursal == null) {
            return "redirect:/sucursales?error=notfound";
        }
        model.addAttribute("sucursal", sucursal);
        return "editarSucursal";
    }

    @PostMapping
    public String crearSucursal(Sucursal sucursal) {
        servicioSucursal.crearSucursal(sucursal);
        return "redirect:/sucursales";
    }

    @PostMapping("/{id}")
    public String modificarSucursal(@PathVariable int id, Sucursal sucursal, Model model) {
        Sucursal modificada = servicioSucursal.modificarSucursal(sucursal);
        if (modificada == null) {
            return "redirect:/sucursales?error=notfound";
        }
        return "redirect:/sucursales/" + id;
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarSucursal(@PathVariable int id) {
        boolean borrada = servicioSucursal.borrarSucursal(id);
        if (!borrada) {
            return "redirect:/sucursales?error=notfound";
        }
        return "redirect:/sucursales";
    }
}
