package com.example.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.List;

import com.example.parranderos.modelo.Frecuentan;

public interface FrecuentanRepository extends JpaRepository<Frecuentan, Integer> {

    @Query(value = "SELECT * FROM frecuentan", nativeQuery = true)
    Collection<Frecuentan> darFrecuentan();

    @Query(value = "SELECT * FROM frecuentan WHERE id_bebedor = :id_bebedor AND id_bar = :id_bar AND TO_CHAR(fecha_visita, 'YYYY-MM-DD') = :fecha_visita AND horario = :horario", nativeQuery = true)
    Frecuentan darFrecuentanPorId(@Param("id_bebedor") Integer id_bebedor, @Param("id_bar") Integer id_bar, @Param("fecha_visita") String fecha_visita, @Param("horario") String horario);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM frecuentan WHERE id_bebedor = :id_bebedor AND id_bar = :id_bar AND TO_CHAR(fecha_visita, 'YYYY-MM-DD') = :fecha_visita AND horario = :horario", nativeQuery = true)
    int eliminarFrecuentan(@Param("id_bebedor") Integer id_bebedor, @Param("id_bar") Integer id_bar, @Param("fecha_visita") String fecha_visita, @Param("horario") String horario);    

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO frecuentan (id_bebedor, id_bar, fecha_visita, horario) VALUES (:id_bebedor, :id_bar, TO_DATE(:fecha_visita, 'YYYY-MM-DD'), :horario)", nativeQuery = true)
    void insertarFrecuentan(@Param("id_bebedor") Integer id_bebedor, @Param("id_bar") Integer id_bar, @Param("fecha_visita") String fecha_visita, @Param("horario") String horario);

    @Query(value = "SELECT * FROM frecuentan WHERE TO_CHAR(fecha_visita, 'YYYY-MM-DD') = :fecha", nativeQuery = true)
    List<Frecuentan> findByFechaVisita(@Param("fecha") String fecha);
}