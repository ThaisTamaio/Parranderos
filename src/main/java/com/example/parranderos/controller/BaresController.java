package com.example.parranderos.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.parranderos.modelo.Bar;
import com.example.parranderos.repositorio.BarRepository;
import com.example.parranderos.servicios.BaresServicio;

@RestController
@RequestMapping("/api/bares")
public class BaresController {

    @Autowired
    private BarRepository barRepository;

    @Autowired
    private BaresServicio baresServicio;

    @GetMapping
    public Collection<Bar> getBares(@RequestParam(required = false) String ciudad, @RequestParam(required = false) String tipo) throws InterruptedException {
        if ((ciudad == null || ciudad.isEmpty()) && (tipo == null || tipo.isEmpty())) {
            return baresServicio.consultarBaresConFantasma();
        } else {
            return barRepository.darBaresPorCiudadYTipoBebida(ciudad, tipo);
        }
    }

    @PostMapping
    public Bar createBar(@RequestBody Bar bar) {
        barRepository.insertarBar(bar.getNombre(), bar.getCiudad(), bar.getPresupuesto(), bar.getCant_sedes());
        return barRepository.encontrarBarRecienCreado(bar.getNombre(), bar.getCiudad());
    }

    @GetMapping("/{id}")
    public Bar getBar(@PathVariable long id) {
        return barRepository.darBar(id);
    }

    @PutMapping("/{id}")
    public void updateBar(@PathVariable long id, @RequestBody Bar bar) {
        barRepository.actualizarBar(id, bar.getNombre(), bar.getCiudad(), bar.getPresupuesto(), bar.getCant_sedes());
    }

    @DeleteMapping("/{id}")
    public void deleteBar(@PathVariable long id) {
        barRepository.eliminarBar(id);
    }

    @PostMapping("/actualizar_bar")
    public void transferir_1() {
        Collection<Bar> bares = barRepository.darBares();
        if (!bares.isEmpty()) {
            Bar primerBar = bares.iterator().next();
            baresServicio.actualizar_bar_con_espera(primerBar.getId(), "Bogota");
            System.out.println("El bar transferido es: " + primerBar.getNombre());
            System.out.println("Su ID es: " + primerBar.getId());
        }
    }

    @PostMapping("/actualizar_bar_inmediato")
    public void transferir_2() {
        Collection<Bar> bares = barRepository.darBares();
        if (!bares.isEmpty()) {
            try {
                Bar primerBar = bares.iterator().next();
                baresServicio.actualizar_bar_sin_espera(primerBar.getId(), "Bogota");
                System.out.println("El bar transferido es: " + primerBar.getNombre());
                System.out.println("Su ID es: " + primerBar.getId());
            } catch (Exception e) {
                System.err.println("Error al transferir el bar: " + e.getMessage());
            }
        }
    }

    @PostMapping("/bloqueo")
    public void consultarBaresSinFantasma() {
        try {
            baresServicio.consultarBaresSinFantasma();
        } catch (Exception e) {
            System.err.println("Error durante la consulta de bares: " + e.getMessage());
        }
    }
}
