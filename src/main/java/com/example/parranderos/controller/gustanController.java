package com.example.parranderos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.parranderos.modelo.Gustan;
import com.example.parranderos.modelo.GustanPK;
import com.example.parranderos.repositorio.BebedorRepository;
import com.example.parranderos.repositorio.BebidaRepository;
import com.example.parranderos.repositorio.GustanRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gustan")
public class GustanController {

    @Autowired
    private BebedorRepository bebedorRepository;

    @Autowired
    private BebidaRepository bebidaRepository;

    @Autowired
    private GustanRepository gustanRepository;

    @GetMapping
    public List<Gustan> getAllGustan() {
        return (List<Gustan>) gustanRepository.darGustan();
    }

    @PostMapping
    public Gustan createGustan(@RequestBody Gustan gustan) {
        GustanPK pk = gustan.getPk();
        Integer idBebedor = pk.getBebedor().getId();
        Integer idBebida = pk.getBebida().getId();

        if (!bebedorRepository.existsById(idBebedor)) {
            throw new RuntimeException("Bebedor no encontrado: " + idBebedor);
        }

        if (!bebidaRepository.existsById(idBebida)) {
            throw new RuntimeException("Bebida no encontrada: " + idBebida);
        }

        gustanRepository.insertarGustan(idBebedor, idBebida);

        return gustanRepository.darGustanPorId(idBebedor, idBebida);
    }

    @GetMapping("/{id_bebedor}/{id_bebida}")
    public Gustan getGustan(@PathVariable Integer id_bebedor, @PathVariable Integer id_bebida) {
        Gustan gustan = gustanRepository.darGustanPorId(id_bebedor, id_bebida);
        if (gustan == null) {
            throw new RuntimeException("Registro gustan no encontrado");
        }
        return gustan;
    }

    @DeleteMapping("/{id_bebedor}/{id_bebida}")
    public void deleteGustan(@PathVariable Integer id_bebedor, @PathVariable Integer id_bebida) {
        gustanRepository.eliminarGustan(id_bebedor, id_bebida);
    }

}