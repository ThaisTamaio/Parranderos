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
    private Bar id_bar;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_bebida", referencedColumnName = "id")
    private Bebida id_bebida;

    @Id
    private String horario;

    public Sirven(){;}

    public Sirven(Bar id_bar, Bebida id_bebida, String horario)
    {
        this.id_bar = id_bar;
        this.id_bebida = id_bebida;
        this.horario = horario;
    }

    public Bar getBar() {
        return id_bar;
    }

    public void setBar(Bar id_bar) {
        this.id_bar = id_bar;
    }

    public Bebida getBebida() {
        return id_bebida;
    }

    public void setBebida(Bebida id_bebida) {
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
