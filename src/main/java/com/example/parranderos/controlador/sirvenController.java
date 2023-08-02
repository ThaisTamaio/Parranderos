package com.example.parranderos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.parranderos.modelo.Bebida;
import com.example.parranderos.modelo.Sirven;
import com.example.parranderos.repositorio.BarRepository;
import com.example.parranderos.repositorio.BebidaRepository;
import com.example.parranderos.repositorio.SirvenRepository;

@Controller
public class sirvenController {

    @Autowired
    private SirvenRepository sirvenRepository;

    @Autowired
    private BarRepository barRepository;

    @Autowired
    private BebidaRepository bebidaRepository;

    @GetMapping("/sirven/new")
    public String sirvenForm(Model model) {
        model.addAttribute("bares", barRepository.darBares());
        model.addAttribute("bebidas", bebidaRepository.darBebidas());
        model.addAttribute("sirven", new Sirven());
        return "sirvenNuevo";
    }

    @PostMapping("/sirven/new/save")
    public String sirvenGuardar(Sirven sirven) {
        sirvenRepository.insertarSirven(sirven.getBar().getId(), sirven.getBebida().getId(), sirven.getHorario());
        return "redirect:/bares";
    }

}
