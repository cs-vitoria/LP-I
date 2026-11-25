package org.example.entidades;

public class Bicicleta {
    private int id;
    private String cor;
    private String modelo;
    private String marca;

    public Bicicleta(String cor, String modelo, String marca){
        this.cor = cor;
        this.modelo = modelo;
        this.marca = marca;
    }

    // construtor usado quando o objeto vem do banco (já possui id)
    public Bicicleta(int id, String cor, String modelo, String marca) {
        this(cor, modelo, marca);
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCor(){
        return cor;
    }
    public String getModelo(){
        return modelo;
    }
    public String getMarca(){
        return marca;
    }
    public void setCor(String cor){
        this.cor = cor;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }

    public String quebrar(){
        return("A bicicleta " + modelo + " " + cor + ", da marca " + marca + ", " +
                "\n está quebrada e com peças faltando.");
    }
    public String andar(){
        return("A bicicleta " + modelo + " " + cor + ", " +
                "\n da marca " + marca + ", está em movimento.");
    }
    public String freiar(){
        return("A bicicleta " + modelo + " " + cor + ", " +
                "\n da marca " + marca + ", está parada.");
    }
}
