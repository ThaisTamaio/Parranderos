package com.example.parranderos.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="gustan")
public class Gustan {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_bebedor", referencedColumnName = "id")
    private Bebedor id_bebedor;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_bebida", referencedColumnName = "id")
    private Bebida id_bebida;

    public Gustan(){;}

    public Gustan(Bebedor id_bebedor, Bebida id_bebida)
    {
        this.id_bebedor = id_bebedor;
        this.id_bebida = id_bebida;
    }

    public Bebedor getBebedor() {
        return id_bebedor;
    }

    public void setBebedor(Bebedor id_bebedor) {
        this.id_bebedor = id_bebedor;
    }

    public Bebida getBebida() {
        return id_bebida;
    }

    public void setBebida(Bebida id_bebida) {
        this.id_bebida = id_bebida;
    }

    @Override
    public String toString() 
    {
        return this.id_bebedor.getId() + "|" + this.id_bebida.getId();
    }
    
}
