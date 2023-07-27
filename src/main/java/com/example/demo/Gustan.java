package com.example.demo;
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
    private Bebedores id_bebedor;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_bebida", referencedColumnName = "id")
    private Bebidas id_bebida;

    public Gustan(){;}

    public Gustan(Bebedores id_bebedor, Bebidas id_bebida)
    {
        this.id_bebedor = id_bebedor;
        this.id_bebida = id_bebida;
    }

    public Bebedores getBebedor() {
        return id_bebedor;
    }

    public void setBebedor(Bebedores id_bebedor) {
        this.id_bebedor = id_bebedor;
    }

    public Bebidas getBebida() {
        return id_bebida;
    }

    public void setBebida(Bebidas id_bebida) {
        this.id_bebida = id_bebida;
    }

    @Override
    public String toString() 
    {
        return this.id_bebedor.getId() + "|" + this.id_bebida.getId();
    }
    
}
