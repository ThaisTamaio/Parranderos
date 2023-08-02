package com.example.parranderos.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "sirven")
public class Sirven {

    @EmbeddedId
    private SirvenPK pk;

    public Sirven() {
        ;
    }

    public Sirven(Bar id_bar, Bebida id_bebida, String horario) {
        super();
        this.pk = new SirvenPK(id_bar, id_bebida, horario);
    }

    public Bar getBar() {
        return this.pk.getBar();
    }

    public void setBar(Bar bar) {
        this.pk.setBar(bar);
    }

    public Bebida getBebida() {
        return this.pk.getBebida();
    }

    public void setBebida(Bebida bebida) {
        this.pk.setBebida(bebida);
    }

    public String getHorario() {
        return this.pk.getHorario();
    }

    public void setHorario(String horario) {
        this.pk.setHorario(horario);
    }

    public SirvenPK getPk() {
        return pk;
    }

    public void setPk(SirvenPK pk) {
        this.pk = pk;
    }

    @Override
    public String toString() {
        return this.pk.getBar().getId() + "|" + this.pk.getBebida().getId() + "|" + this.pk.getHorario();
    }

}
