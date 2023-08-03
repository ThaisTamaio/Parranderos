package com.example.parranderos.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Collection;
import com.example.parranderos.modelo.Frecuentan;

public interface FrecuentanRepository  extends JpaRepository<Frecuentan, Integer>{

    @Query(value = "SELECT * FROM frecuentan", nativeQuery = true)
    Collection<Frecuentan> darFrecuentan();

    @Query(value = "SELECT * FROM frecuentan WHERE id_bebedor = :id_bebedor AND id_bar = :id_bar AND fecha_visita = :fecha_visita AND horario = :horario", nativeQuery = true)
    Frecuentan darFrecuentanPorId(@Param("id_bebedor") Integer id_bebedor, @Param("id_bar") Integer id_bar, @Param("fecha_visita") Date fecha_visita, @Param("horario") String horario);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM frecuentan WHERE id_bebedor = :id_bebedor AND id_bar = :id_bar AND fecha_visita = :fecha_visita AND horario = :horario", nativeQuery = true)
    void eliminarFrecuentan(@Param("id_bebedor") Integer id_bebedor, @Param("id_bar") Integer id_bar, @Param("fecha_visita") Date fecha_visita, @Param("horario") String horario);

    @Modifying
    @Transactional
    @Query(value = "UPDATE frecuentan SET id_bebedor = :id_bebedor_actualizado, id_bar = :id_bar_actualizado, fecha_visita = :fecha_visita_actualizado, horario = :horario_actualizado WHERE id_bebedor = :id_bebedor AND id_bar = :id_bar AND fecha_visita = :fecha_visita AND horario = :horario", nativeQuery = true)
    void actualizarFrecuentan(@Param("id_bebedor") Integer id_bebedor, @Param("id_bar") Integer id_bar, @Param("fecha_visita") Date fecha_visita, @Param("horario") String horario, @Param("id_bebedor_actualizado") Integer id_bebedor_actualizado, @Param("id_bar_actualizado") Integer id_bar_actualizado, @Param("fecha_visita_actualizado") Date fecha_visita_actualizado, @Param("horario_actualizado") String horario_actualizado);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO frecuentan (id_bebedor, id_bar, fecha_visita, horario) VALUES (:id_bebedor, :id_bar, :fecha_visita, :horario)", nativeQuery = true)
    void insertarFrecuentan(@Param("id_bebedor") Integer id_bebedor, @Param("id_bar") Integer id_bar, @Param("fecha_visita") Date fecha_visita, @Param("horario") String horario);

}

