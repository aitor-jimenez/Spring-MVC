package es.neesis.mvcdemo.controller;

import es.neesis.mvcdemo.dtos.OutCuentaDTO;
import es.neesis.mvcdemo.modelos.Cuenta;
import es.neesis.mvcdemo.servicios.ServicioCuenta;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("cuentas")
public class CuentaControllerREST {

    private ServicioCuenta servicioCuenta;

    public CuentaControllerREST(ServicioCuenta servicioCuenta) {
        this.servicioCuenta = servicioCuenta;
    }

    @GetMapping("/{idCliente}/all")
    @ResponseBody
    public ResponseEntity<List<Cuenta>> listarCuentas(@PathVariable Long clienteId) {
        List<Cuenta> cuentas;
        try {
            cuentas = servicioCuenta.getTodasCuentas(clienteId);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        if (cuentas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cuentas);
    }

    @PostMapping("/{idCliente}/")
    public ResponseEntity<Void> darAltaCuenta(@PathVariable Long idCliente, @RequestBody Cuenta cuenta) {
        try {
            servicioCuenta.darAltaCuenta(idCliente, cuenta);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idCliente}/{numCuenta}")
    public ResponseEntity<Void> modificarCuenta(@PathVariable Long idCliente, @PathVariable String numCuenta, @RequestBody Cuenta cuenta) {
        try {
            servicioCuenta.modificarCuenta(idCliente, numCuenta, cuenta);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idCliente}/{numCuenta}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long idCliente, @PathVariable String numCuenta) {
        try {
            servicioCuenta.eliminarCuenta(idCliente, numCuenta);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idCliente}/{numCuenta}")
    public ResponseEntity<OutCuentaDTO> obtenerDetalles(@PathVariable Long idCliente, @PathVariable String numCuenta) {
        OutCuentaDTO cuenta;
        try {
            cuenta = servicioCuenta.getDetalles(idCliente, numCuenta);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cuenta);
    }
}
