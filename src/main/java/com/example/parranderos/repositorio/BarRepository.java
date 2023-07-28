package com.example.parranderos.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import com.example.parranderos.modelo.Bar;

public interface BarRepository  extends JpaRepository<Bar, Integer>{

    @Query(value = "SELECT * FROM bares", nativeQuery = true)
    Collection<Bar> darBares();

    @Query(value = "SELECT * FROM bares WHERE id = :id", nativeQuery = true)
    Bar darBar(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM bares WHERE id = :id", nativeQuery = true)
    void eliminarBar(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE bares SET nombre = :nombre, ciudad = :ciudad, presupuesto = :presupuesto, cant_sedes = :cant_sedes WHERE id = :id", nativeQuery = true)
    void actualizarBar(@Param("id") long id, @Param("nombre") String nombre, @Param("ciudad") String ciudad,
            @Param("presupuesto") String presupuesto, @Param("cant_sedes") Integer cant_sedes);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO bares (id, nombre, ciudad, presupuesto, cant_sedes) VALUES ( parranderos_sequence.nextval , :nombre, :ciudad, :presupuesto, :cant_sedes)", nativeQuery = true)
    void insertarBar(@Param("nombre") String nombre, @Param("ciudad") String ciudad,
            @Param("presupuesto") String presupuesto, @Param("cant_sedes") Integer cant_sedes);

}



