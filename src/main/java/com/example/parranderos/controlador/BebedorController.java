package com.example.parranderos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.parranderos.modelo.Bebedor;
import com.example.parranderos.repositorio.BebedorRepository;

@Controller
public class BebedorController {

    @Autowired
    private BebedorRepository bebedorRepository;

    @GetMapping("/bebedores")
    public String bebedores(Model model) {
        int darNumeroDeBebedoresQueGustanDeBebidasConMayorGradoAlcohol = bebedorRepository
                .darNumeroDeBebedoresQueGustanDeBebidasConMayorGradoAlcohol();
        int darNumeroDeBebedoresQueGustanDeBebidasConMenorGradoAlcohol = bebedorRepository
                .darNumeroDeBebedoresQueGustanDeBebidasConMenorGradoAlcohol();
        model.addAttribute("darNumeroDeBebedoresQueGustanDeBebidasConMayorGradoAlcohol",
                darNumeroDeBebedoresQueGustanDeBebidasConMayorGradoAlcohol);
        model.addAttribute("darNumeroDeBebedoresQueGustanDeBebidasConMenorGradoAlcohol",
                darNumeroDeBebedoresQueGustanDeBebidasConMenorGradoAlcohol);
        model.addAttribute("bebedores", bebedorRepository.darBebedores());
        return "bebedores";
    }

    @GetMapping("/bebedores/new")
    public String bebedorForm(Model model) {
        model.addAttribute("bebedor", new Bebedor());
        return "bebedorNuevo";
    }

    @PostMapping("/bebedores/new/save")
    public String bebedorGuardar(@ModelAttribute Bebedor bebedor) {
        bebedorRepository.insertarBebedor(bebedor.getNombre(), bebedor.getCiudad(), bebedor.getPresupuesto());
        return "redirect:/bebedores";
    }

    @GetMapping("/bebedores/{id}/edit")
    public String bebedorEditarForm(@PathVariable("id") long id, Model model) {
        Bebedor bebedor = bebedorRepository.darBebedor(id);
        if (bebedor != null) {
            model.addAttribute("bebedor", bebedor);
            return "bebedorEditar";
        } else {
            return "redirect:/bebedores";
        }
    }

    @PostMapping("/bebedores/{id}/edit/save")
    public String bebedorEditarGuardar(@PathVariable("id") long id, @ModelAttribute Bebedor bebedor) {
        bebedorRepository.actualizarBebedor(((long) id), bebedor.getNombre(), bebedor.getCiudad(),
                bebedor.getPresupuesto());
        return "redirect:/bebedores";
    }

    @GetMapping("/bebedores/{id}/delete")
    public String bebedorBorrar(@PathVariable("id") long id) {
        bebedorRepository.eliminarBebedor(id);
        return "redirect:/bebedores";
    }

}
