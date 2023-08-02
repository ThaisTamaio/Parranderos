package com.example.parranderos.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.parranderos.modelo.Bebida;
import com.example.parranderos.repositorio.BebidaRepository;
import com.example.parranderos.repositorio.Tipo_bebidaRepository;
import com.example.parranderos.repositorio.BebidaRepository.RespuestaInformacionBebidas;

@Controller
public class bebidaController {

    @Autowired
    private BebidaRepository bebidaRepository;

    @Autowired
    private Tipo_bebidaRepository tipo_bebidaRepository;

    @GetMapping("/bebidas")
    public String bebidas(Model model) {
        Collection<RespuestaInformacionBebidas> informacion = bebidaRepository.darInformacionBebidas();
        model.addAttribute("totalBebidas", informacion.iterator().next().getTOTAL_BEBIDAS());
        model.addAttribute("promedioGrado", informacion.iterator().next().getPROMEDIO_GRADO());
        model.addAttribute("mayorGrado", informacion.iterator().next().getMAYOR_GRADO());
        model.addAttribute("menorGrado", informacion.iterator().next().getMENOR_GRADO());
        model.addAttribute("bebidas", bebidaRepository.darBebidas());
        return "bebidas";
    }

    @GetMapping("/bebidas/new")
    public String bebidaForm(Model model) {
        model.addAttribute("bebida", new Bebida());
        model.addAttribute("tipos", tipo_bebidaRepository.darTipos_bebida());
        return "bebidaNuevo";
    }

    @PostMapping("/bebidas/new/save")
    public String bebidaGuardar(@ModelAttribute Bebida bebida) {
        bebidaRepository.insertarBebida(bebida.getNombre(), bebida.getGrado_alcohol(), bebida.getTipo().getId());
        return "redirect:/bebidas";
    }

    @GetMapping("/bebidas/{id}/edit")
    public String bebidaEditarForm(@PathVariable("id") long id, Model model) {
        Bebida bebida = bebidaRepository.darBebida(id);
        if (bebida != null) {
            model.addAttribute("bebida", bebida);
            model.addAttribute("tipos", tipo_bebidaRepository.darTipos_bebida());
            return "bebidaEditar";
        } else {
            return "redirect:/bebidas";
        }
    }

    @PostMapping("/bebidas/{id}/edit/save")
    public String bebidaEditarGuardar(@PathVariable("id") long id, @ModelAttribute Bebida bebida) {
        bebidaRepository.actualizarBebida(((long) id), bebida.getNombre(), bebida.getGrado_alcohol(),
                bebida.getTipo().getId());
        return "redirect:/bebidas";
    }

    @GetMapping("/bebidas/{id}/delete")
    public String bebidaBorrar(@PathVariable("id") long id) {
        bebidaRepository.eliminarBebida(id);
        return "redirect:/bebidas";
    }

}
