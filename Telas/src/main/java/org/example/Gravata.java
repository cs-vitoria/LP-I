package org.example;

public class Gravata {
    private String textura;
    private String estampa;
    private String modelo;
    public Gravata(String textura, String estampa, String modelo) {
        this.textura = textura;
        this.estampa = estampa;
        this.modelo = modelo;
    }
    public String getTextura(){
        return textura;
    }
    public String getEstampa(){
        return estampa;
    }
    public String getModelo(){
        return modelo;
    }
    public void setTecido (String textura){
        this.textura = textura;
    }
    public void setEstampa(String estampa){
        this.estampa = estampa;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    String amarrar() {
        return("Sua gravata " + modelo + " " + textura + "," +
                "\n  com estampa " + estampa + " está amarrada.");
    }
    String cortar() {
        return("Sua gravata " + modelo + " " + textura + ", com estampa " +
                "\n " + estampa + " foi cortada para vestir melhor.");
    }
    String pendurar() {
        return("Sua gravata " + modelo + " " + textura + ", com " +
                "\n estampa " + estampa + " está pendurada no cabide.");
    }
}
