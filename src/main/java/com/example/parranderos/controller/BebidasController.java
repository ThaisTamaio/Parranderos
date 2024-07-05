package com.example.parranderos.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.parranderos.modelo.Bebida;
import com.example.parranderos.modelo.TipoBebida;
import com.example.parranderos.repositorio.BebidaRepository;
import com.example.parranderos.repositorio.TipoBebidaRepository;

@RestController
@RequestMapping("/api/bebidas")
public class BebidasController {

    @Autowired
    private BebidaRepository bebidaRepository;

    @Autowired
    private TipoBebidaRepository tipoBebidaRepository;

    @GetMapping
    public Collection<Bebida> getBebidas(@RequestParam(required = false) String ciudad, @RequestParam(required = false) String minGrado, @RequestParam(required = false) String maxGrado) {
        if ((ciudad == null || ciudad.isEmpty()) || (minGrado == null || minGrado.isEmpty()) || (maxGrado == null || maxGrado.isEmpty())) {
            return bebidaRepository.darBebidas();
        } else {
            return bebidaRepository.darBebidasPorCiudadYGrado(ciudad, Integer.parseInt(minGrado), Integer.parseInt(maxGrado));
        }
    }

    @PostMapping
    public Bebida createBebida(@RequestBody Bebida bebida) {
        TipoBebida tipoBebida = tipoBebidaRepository.findById(bebida.getTipo().getId()).orElse(null);
        if (tipoBebida != null) {
            bebidaRepository.insertarBebida(bebida.getNombre(), bebida.getGrado_alcohol(), tipoBebida.getId());
            return bebidaRepository.encontrarBebidaRecienCreada(bebida.getNombre(), tipoBebida.getId());
        } else {
            throw new IllegalArgumentException("Tipo de bebida no encontrado");
        }
    }

    @GetMapping("/{id}")
    public Bebida getBebida(@PathVariable long id) {
        return bebidaRepository.darBebida(id);
    }

    @PutMapping("/{id}")
    public void updateBebida(@PathVariable long id, @RequestBody Bebida bebida) {
        TipoBebida tipoBebida = tipoBebidaRepository.findById(bebida.getTipo().getId()).orElse(null);
        if (tipoBebida != null) {
            bebidaRepository.actualizarBebida(id, bebida.getNombre(), bebida.getGrado_alcohol(), tipoBebida.getId());
        } else {
            throw new IllegalArgumentException("Tipo de bebida no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBebida(@PathVariable long id) {
        bebidaRepository.eliminarBebida(id);
    }
}
