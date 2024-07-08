package uniandes.edu.co.parranderos.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import uniandes.edu.co.parranderos.modelo.Bar;
import uniandes.edu.co.parranderos.repositorio.BarRepository;

import org.springframework.ui.Model;


@RestController
public class BaresController {

    @Autowired
    private BarRepository barRepository;

    @GetMapping("/bares")
    public Collection<Bar> bares() {
        return barRepository.darBares();
    }


    @GetMapping("/bares/new")
    public String barForm(Model model) {
        model.addAttribute("bar", new Bar());
        return "barNuevo";
    }

    @PostMapping("/bares/new/save")
    public String barGuardar(@RequestBody Bar bar) {
        barRepository.insertarBar(bar.getNombre(), bar.getCiudad(), bar.getPresupuesto(), bar.getCant_sedes());
        return "redirect:/bares";
    }

    @GetMapping("/bares/{id}/edit")
    public String barEditarForm(@PathVariable("id") int id, Model model) {
        Bar bar = barRepository.darBar(id);
        if (bar != null) {
            model.addAttribute("bar", bar);
            return "barEditar";
        } else {
            return "redirect:/bares";
        }
    }

    @PostMapping("/bares/{id}/edit/save")
    public String barEditarGuardar(@PathVariable("id") long id, @RequestBody Bar bar) {
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