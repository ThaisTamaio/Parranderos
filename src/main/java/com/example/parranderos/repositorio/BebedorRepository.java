package com.example.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import com.example.parranderos.modelo.Bebedor;


public interface BebedorRepository extends JpaRepository<Bebedor, Integer> {

    public interface Respuesta{
        String getCiudad();
        String getPresupuesto();
        int getNumeroDeBebedores();
       
    }

    @Query(value = "SELECT * FROM bebedores", nativeQuery = true)
    Collection<Bebedor> darBebedores();

    @Query(value = "SELECT * FROM bebedores WHERE id = :id", nativeQuery = true)
    Bebedor darBebedor(@Param("id") long id);

    // Consulta avanzada
    @Query(value="SELECT ciudad, presupuesto, count(*) AS numeroDeBebedores\r\n" + //
                    "FROM bebedores\r\n" + //
                    "GROUP BY ciudad, presupuesto\r\n" , nativeQuery=true)
    Collection<Respuesta> dar_bebedores_por_presupuesto_y_ciudad();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM bebedores WHERE id = :id", nativeQuery = true) 
    void eliminarBebedor(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE bebedores SET nombre = :nombre, ciudad = :ciudad, presupuesto = :presupuesto WHERE id = :id", nativeQuery = true)
    void actualizarBebedor(@Param("id") long id, @Param("nombre") String nombre, @Param("ciudad") String ciudad,
            @Param("presupuesto") String presupuesto);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO bebedores (id, nombre, ciudad, presupuesto) VALUES ( parranderos_sequence.nextval , :nombre, :ciudad, :presupuesto)", nativeQuery = true)
    void insertarBebedor(@Param("nombre") String nombre, @Param("ciudad") String ciudad,
            @Param("presupuesto") String presupuesto);

}
