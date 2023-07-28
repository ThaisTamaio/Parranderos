package com.example.parranderos.repositorio;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import com.example.parranderos.modelo.Bebida;

public interface BebidaRepository extends JpaRepository<Bebida, Integer> {

    @Query(value = "SELECT * FROM bebidas", nativeQuery = true)
    Collection<Bebida> darBebidas();

    @Query(value = "SELECT * FROM bebidas WHERE id = :id", nativeQuery = true)
    Bebida darBebida(@Param("id") long id);


    //Consulta avanzada
    @Query(value = "SELECT B.*\r\n" + //
                    "FROM bebidas B\r\n" + //
                    "INNER JOIN sirven SB ON B.ID = SB.ID_BEBIDA\r\n" + //
                    "INNER JOIN bares BR ON SB.ID_BAR = BR.ID\r\n" + //
                    "WHERE SB.HORARIO = :horario AND BR.PRESUPUESTO = :presupuesto", nativeQuery=true)
    Collection<Bebida> darBebidasPorHorarioYPresupuesto(@Param("horario") String horario,@Param("presupuesto") String presupuesto);



    @Modifying
    @Transactional
    @Query(value = "DELETE FROM bebidas WHERE id = :id", nativeQuery = true)
    void eliminarBebida(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE bebidas SET nombre = :nombre, grado_alcohol = :grado_alcohol, tipo = :tipo WHERE id = :id", nativeQuery = true)
    void actualizarBebida(@Param("id") long id, @Param("nombre") String nombre, @Param("grado_alcohol") Integer grado_alcohol,
            @Param("tipo") Integer tipo);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO bebidas (id, nombre, grado_alcohol, tipo) VALUES ( parranderos_sequence.nextval , :nombre, :grado_alcohol, :tipo)", nativeQuery = true)
    void insertarBebida(@Param("nombre") String nombre, @Param("grado_alcohol") Integer grado_alcohol,
            @Param("tipo") Integer tipo);

}
