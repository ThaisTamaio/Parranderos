package com.example.parranderos.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="sirven")
public class Sirven {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_bar", referencedColumnName = "id")
    private Bares id_bar;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_bebida", referencedColumnName = "id")
    private Bebidas id_bebida;

    @Id
    private String horario;

    public Sirven(){;}

    public Sirven(Bares id_bar, Bebidas id_bebida, String horario)
    {
        this.id_bar = id_bar;
        this.id_bebida = id_bebida;
        this.horario = horario;
    }

    public Bares getBar() {
        return id_bar;
    }

    public void setBar(Bares id_bar) {
        this.id_bar = id_bar;
    }

    public Bebidas getBebida() {
        return id_bebida;
    }

    public void setBebida(Bebidas id_bebida) {
        this.id_bebida = id_bebida;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario){
        this.horario = horario;
    }

    @Override
    public String toString() 
    {
        return this.id_bar.getId() + "|" + this.id_bebida.getId() + "|" + this.horario;
    }
    
}
