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

    public interface RespuestaBebedoryBares{
        int getId();
        String getNombre();
        String getCiudad();
        String getPresupuesto();
        Integer getIdBar();

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

    // Consulta avanzada
    @Query(value="SELECT B.*, F.id_bar AS IDBAR\r\n" + //
                    "FROM frecuentan F\r\n" + //
                    "RIGHT OUTER JOIN bebedores B ON B.id = F.id_bebedor\r\n", nativeQuery=true)
    Collection<RespuestaBebedoryBares> darTodosLosBebedoresYLosBaresQueFrencuentan();

    // Consulta avanzada
    @Query(value="SELECT UNIQUE B.*\r\n" + //
                    "FROM (SELECT G.id_bebedor\r\n" + //
                                    "FROM (SELECT B.id\r\n" + //
                                                    "FROM bebidas B\r\n" + //
                                                    "WHERE grado_alcohol = (SELECT MAX(grado_alcohol) AS GRADO_MAX FROM bebidas)) B \r\n" + //
                                    "INNER JOIN gustan G ON B.ID = G.id_bebida) IDB\r\n" + //
                    "INNER JOIN bebedores B ON IDB.id_bebedor = B.id\r\n" + //
                    "ORDER BY B.NOMBRE ASC\r\n", nativeQuery=true)
    Collection<Bebedor> darBebedoresQueGustanDeBebidasConMayorGradoAlcohol();

    // Consulta avanzada
    @Query(value="SELECT UNIQUE B.*\r\n" + //
                    "FROM (SELECT G.id_bebedor\r\n" + //
                                    "FROM (SELECT B.id\r\n" + //
                                                    "FROM bebidas B\r\n" + //
                                                    "WHERE grado_alcohol = (SELECT MIN(grado_alcohol) AS GRADO_MIN FROM bebidas)) B \r\n" + //
                                    "INNER JOIN gustan G ON B.ID = G.id_bebida) IDB\r\n" + //
                    "INNER JOIN bebedores B ON IDB.id_bebedor = B.id\r\n" + //
                    "ORDER BY B.NOMBRE ASC\r\n", nativeQuery=true)
    Collection<Bebedor> darBebedoresQueGustanDeBebidasConMenorGradoAlcohol();
    
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
