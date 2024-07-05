package com.example.parranderos.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.parranderos.modelo.TipoBebida;
import com.example.parranderos.repositorio.TipoBebidaRepository;

@RestController
@RequestMapping("/api/tipos-bebida")
public class TipoBebidaController {

    @Autowired
    private TipoBebidaRepository tipoBebidaRepository;

    @GetMapping
    public Collection<TipoBebida> getTiposBebida() {
        return tipoBebidaRepository.darTipos_bebida();
    }

    @GetMapping("/{id}")
    public TipoBebida getTipoBebida(@PathVariable long id) {
        return tipoBebidaRepository.darTipoBebida(id);
    }

    @PostMapping
    public TipoBebida createTipoBebida(@RequestBody TipoBebida tipoBebida) {
        tipoBebidaRepository.insertarTipoBebida(tipoBebida.getNombre());
        return tipoBebidaRepository.encontrarTipoBebidaRecienCreado(tipoBebida.getNombre());
    }

    @PutMapping("/{id}")
    public void updateTipoBebida(@PathVariable long id, @RequestBody TipoBebida tipoBebida) {
        tipoBebidaRepository.actualizarTipoBebida(id, tipoBebida.getNombre());
    }

    @DeleteMapping("/{id}")
    public void deleteTipoBebida(@PathVariable long id) {
        tipoBebidaRepository.eliminarTipoBebida(id);
    }
}