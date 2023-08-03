package com.example.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.parranderos.modelo.Bebedor;
import com.example.parranderos.modelo.Bebida;
import com.example.parranderos.modelo.Gustan;
import com.example.parranderos.modelo.GustanPK;
import com.example.parranderos.repositorio.BebedorRepository;
import com.example.parranderos.repositorio.BebidaRepository;
import com.example.parranderos.repositorio.GustanRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GustanController {

    @Autowired
    private BebedorRepository bebedorRepository;

    @Autowired
    private BebidaRepository bebidaRepository;

    @Autowired
    private GustanRepository gustanRepository;

    @GetMapping("/gustan/new")
    public String gustanForm(Model model) {
        model.addAttribute("bebidas", bebidaRepository.darBebidas());
        model.addAttribute("bebedores", bebedorRepository.darBebedores());
        return "gustanNuevo";
    }

    @PostMapping("/gustan/new/save")
    public String gustanGuardar(@ModelAttribute("id_bebida") Long idBebida,
            @ModelAttribute("id_bebedor") Long idBebedor) {

        Bebedor bebedor = bebedorRepository.darBebedor(idBebedor);
        Bebida bebida = bebidaRepository.darBebida(idBebida);
        GustanPK pk = new GustanPK(bebedor, bebida);
        Gustan gustan = new Gustan();
        gustan.setPk(pk);
        gustanRepository.insertarGustan(gustan.getPk().getBebedor().getId(), gustan.getPk().getBebida().getId());
        return "redirect:/bebidas";
    }
}
