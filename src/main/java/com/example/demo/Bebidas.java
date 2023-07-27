package com.example.demo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="bebidas")
public class Bebidas {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    private String nombre;
    
    private String grado_alcohol;

    @ManyToOne 
    @JoinColumn(name = "tipo", referencedColumnName = "id") 
    private Tipos_bebida tipo; 
    
    public Bebidas(){;}
    
    public Bebidas(String nombre, String color, String grado_alcohol, Tipos_bebida tipo)
    {
        this.nombre = nombre;
        this.grado_alcohol = grado_alcohol;
        this.tipo = tipo;
    } 
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getGrado_alcohol() {
        return grado_alcohol;
    }
    
    public void setGrado_alcohol(String grado_alcohol){
        this.grado_alcohol = grado_alcohol;
    }
    
    public Tipos_bebida getTipo() {
        return tipo;
    }
    
    public void setTipo(Tipos_bebida tipo){
        this.tipo = tipo;
    }

    @Override
    public String toString() 
    {
        return this.nombre+"|"+this.grado_alcohol+"|"+this.tipo;
    }
    
}
