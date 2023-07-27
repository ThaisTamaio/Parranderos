package com.example.demo;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="frecuentan")
public class Frecuentan {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_bebedor", referencedColumnName = "id")
    private Bebedores bebedor;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_bar", referencedColumnName = "id")
    private Bares bar;

    @Id
    private Date fecha_visita;

    @Id
    private String horario;

    public Frecuentan(){;}

    public Frecuentan(Bebedores bebedor, Bares bar, Date fecha_visita, String horario)
    {
        this.bebedor = bebedor;
        this.bar = bar;
        this.fecha_visita = fecha_visita;
        this.horario = horario;
    }

    public Bebedores getBebedor() {
        return bebedor;
    }

    public void setBebedor(Bebedores bebedor) {
        this.bebedor = bebedor;
    }

    public Bares getBar() {
        return bar;
    }

    public void setBar(Bares bar) {
        this.bar = bar;
    }

    public Date getFecha_visita() {
        return fecha_visita;
    }

    public void setFecha_visita(Date fecha_visita) {
        this.fecha_visita = fecha_visita;
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
        return this.bebedor.getId() + "|" + this.bar.getId() + "|" + this.fecha_visita + "|" + this.horario;

    }
}
