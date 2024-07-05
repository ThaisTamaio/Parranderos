package com.example.parranderos.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.example.parranderos.modelo.Frecuentan;
import com.example.parranderos.modelo.FrecuentanPK;
import com.example.parranderos.repositorio.BarRepository;
import com.example.parranderos.repositorio.BebedorRepository;
import com.example.parranderos.repositorio.FrecuentanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/frecuentan")
public class FrecuentanController {

    private static final Logger logger = LoggerFactory.getLogger(FrecuentanController.class);

    @Autowired
    private BebedorRepository bebedorRepository;

    @Autowired
    private BarRepository barRepository;

    @Autowired
    private FrecuentanRepository frecuentanRepository;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GetMapping
    public List<Frecuentan> getAllFrecuentan() {
        return (List<Frecuentan>) frecuentanRepository.darFrecuentan();
    }

    @PostMapping
    public Frecuentan createFrecuentan(@RequestBody Frecuentan frecuentan) {
        FrecuentanPK pk = frecuentan.getPk();
        Integer idBebedor = pk.getBebedor().getId();
        Integer idBar = pk.getBar().getId();
        String fechaVisita = pk.getFecha_visita().format(formatter);
        String horario = pk.getHorario();

        if (!bebedorRepository.existsById(idBebedor)) {
            throw new RuntimeException("Bebedor no encontrado");
        }

        if (!barRepository.existsById(idBar)) {
            throw new RuntimeException("Bar no encontrado");
        }

        frecuentanRepository.insertarFrecuentan(idBebedor, idBar, fechaVisita, horario);

        return frecuentanRepository.darFrecuentanPorId(idBebedor, idBar, fechaVisita, horario);
    }

    @GetMapping("/{id_bebedor}/{id_bar}/{fecha_visita}/{horario}")
    public Frecuentan getFrecuentan(@PathVariable Integer id_bebedor, @PathVariable Integer id_bar, 
                                    @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha_visita, 
                                    @PathVariable String horario) {
        String fechaVisita = fecha_visita.format(formatter);
        Frecuentan frecuentan = frecuentanRepository.darFrecuentanPorId(id_bebedor, id_bar, fechaVisita, horario);
        if (frecuentan == null) {
            throw new RuntimeException("Registro frecuentan no encontrado");
        }
        return frecuentan;
    }

    @GetMapping("/by-date")
    public List<Frecuentan> getFrecuentanByDate(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return frecuentanRepository.findByFechaVisita(fecha.format(formatter));
    }

    @DeleteMapping("/{id_bebedor}/{id_bar}/{fecha_visita}/{horario}")
    public void deleteFrecuentan(@PathVariable Integer id_bebedor, @PathVariable Integer id_bar, 
                                @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha_visita, 
                                @PathVariable String horario) {
        String fechaVisita = fecha_visita.format(formatter);
        logger.info("Intentando eliminar registro: id_bebedor={}, id_bar={}, fecha_visita={}, horario={}", 
                    id_bebedor, id_bar, fechaVisita, horario);

        int filasAfectadas = frecuentanRepository.eliminarFrecuentan(id_bebedor, id_bar, fechaVisita, horario);

        if (filasAfectadas == 0) {
            logger.error("No se encontró ningún registro para eliminar.");
        } else {
            logger.info("El registro fue eliminado exitosamente.");
        }
    }

}