package com.example.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.parranderos.modelo.Bar;
import com.example.parranderos.repositorio.BarRepository;

@Controller
public class BarController {

    @Autowired
    private BarRepository barRepository;

    @GetMapping("/bares")
    public String bares(Model model) {
        int NumeroDeBaresQueSirvenBebidasConMayorGradoAlcohol = barRepository
                .darNumeroDeBaresQueSirvenBebidasConMayorGradoAlcohol();
        int NumeroDeBaresQueSirvenBebidasConMenorGradoAlcohol = barRepository
                .darNumeroDeBaresQueSirvenBebidasConMenorGradoAlcohol();
        model.addAttribute("NumeroDeBaresQueSirvenBebidasConMayorGradoAlcohol",
                NumeroDeBaresQueSirvenBebidasConMayorGradoAlcohol);
        model.addAttribute("NumeroDeBaresQueSirvenBebidasConMenorGradoAlcohol",
                NumeroDeBaresQueSirvenBebidasConMenorGradoAlcohol);
        model.addAttribute("bares", barRepository.darBares());
        return "bares";
    }

    @GetMapping("/bares/new")
    public String barForm(Model model) {
        model.addAttribute("bar", new Bar());
        return "barNuevo";
    }

    @PostMapping("/bares/new/save")
    public String barGuardar(@ModelAttribute Bar bar) {
        barRepository.insertarBar(bar.getNombre(), bar.getCiudad(), bar.getPresupuesto(), bar.getCant_sedes());
        return "redirect:/bares";
    }

    @GetMapping("/bares/{id}/edit")
    public String barEditarForm(@PathVariable("id") long id, Model model) {
        Bar bar = barRepository.darBar(id);
        if (bar != null) {
            model.addAttribute("bar", bar);
            return "barEditar";
        } else {
            return "redirect:/bares";
        }
    }

    @PostMapping("/bares/{id}/edit/save")
    public String barEditarGuardar(@PathVariable("id") long id, @ModelAttribute Bar bar) {
        barRepository.actualizarBar(((long) id), bar.getNombre(), bar.getCiudad(), bar.getPresupuesto(),
                bar.getCant_sedes());
        return "redirect:/bares";
    }

    @GetMapping("/bares/{id}/delete")
    public String barEliminar(@PathVariable("id") long id) {
        barRepository.eliminarBar(id);
        return "redirect:/bares";
    }

}
