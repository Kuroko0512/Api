package com.example.api.Models;

public class Antojito {
    private int idAntojito;
    private String nombreAntojito;
    private double precio;

    public Antojito(int idAntojito, String nombreAntojito, double precio) {
        this.idAntojito = idAntojito;
        this.nombreAntojito = nombreAntojito;
        this.precio = precio;
    }

    public int getIdAntojito() {
        return idAntojito;
    }

    public String getNombreAntojito() {
        return nombreAntojito;
    }

    public double getPrecio() {
        return precio;
    }
}
