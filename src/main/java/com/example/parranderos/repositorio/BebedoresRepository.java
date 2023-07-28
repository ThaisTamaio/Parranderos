package com.example.parranderos.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import com.example.parranderos.modelo.Bebedor;


public interface BebedoresRepository  extends JpaRepository<Bebedor, Integer>{
    @Query(value = "SELECT * FROM bebedores", nativeQuery=true)
    Collection<Bebedor> darBebedores();

    @Modifying
    @Transactional
    @Query(value = "delete from bebedores where id=:id", nativeQuery=true)
    void eliminarBebedor(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "update bebedores b set b.nombre = :nombre, b.ciudad = :ciudad, b.presupuesto = :presupuesto where b.id = :id", nativeQuery=true)
    void actualizarBebedor(@Param("id") long id, @Param("nombre") String nombre, @Param("ciudad") String ciudad, @Param("presupuesto") String presupuesto);

    @Modifying
    @Transactional
    @Query(value = "insert into bebedores (id, nombre, ciudad, presupuesto) values ( parranderos_sequence.nextval , :nombre, :ciudad, :presupuesto)", nativeQuery=true)
    void insertarBebedor(@Param("nombre") String nombre, @Param("ciudad") String ciudad, @Param("presupuesto") String presupuesto);
    
    
}
