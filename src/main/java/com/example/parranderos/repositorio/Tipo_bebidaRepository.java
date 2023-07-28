package com.example.parranderos.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import com.example.parranderos.modelo.Tipo_bebida;

public interface Tipo_bebidaRepository  extends JpaRepository<Tipo_bebida, Integer>{

    @Query(value = "SELECT * FROM tipos_bebida", nativeQuery = true)
    Collection<Tipo_bebida> darTipos_bebida();

    @Query(value = "SELECT * FROM tipos_bebida WHERE id = :id", nativeQuery = true)
    Tipo_bebida darTipo_bebida(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tipos_bebida WHERE id = :id", nativeQuery = true)
    void eliminarTipo_bebida(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tipos_bebida SET nombre = :nombre WHERE id = :id", nativeQuery = true)
    void actualizarTipo_bebida(@Param("id") long id, @Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tipos_bebida (id, nombre) VALUES ( parranderos_sequence.nextval , :nombre)", nativeQuery = true)
    void insertarTipo_bebida(@Param("nombre") String nombre);
    
}
