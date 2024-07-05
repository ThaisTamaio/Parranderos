package com.example.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.parranderos.modelo.Bebedor;
import com.example.parranderos.repositorio.BebedorRepository;
import java.util.Collection;

@RestController
@RequestMapping("/api/bebedores")
public class BebedoresController {

    @Autowired
    private BebedorRepository bebedorRepository;

    @GetMapping
    public Collection<Bebedor> getBebedores(@RequestParam(required = false) String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            return bebedorRepository.darBebedoresPorNombre(nombre);
        } else {
            return bebedorRepository.darBebedores();
        }
    }

    @PostMapping
    public Bebedor createBebedor(@RequestBody Bebedor bebedor) {
        bebedorRepository.insertarBebedor(bebedor.getNombre(), bebedor.getCiudad(), bebedor.getPresupuesto());
        return bebedorRepository.encontrarBebedorRecienCreado(bebedor.getNombre(), bebedor.getCiudad());
    }

    @GetMapping("/{id}")
    public Bebedor getBebedor(@PathVariable long id) {
        return bebedorRepository.darBebedor(id);
    }

    @PutMapping("/{id}")
    public void updateBebedor(@PathVariable long id, @RequestBody Bebedor bebedor) {
        bebedorRepository.actualizarBebedor(id, bebedor.getNombre(), bebedor.getCiudad(), bebedor.getPresupuesto());
    }

    @DeleteMapping("/{id}")
    public void deleteBebedor(@PathVariable long id) {
        bebedorRepository.eliminarBebedor(id);
    }
}