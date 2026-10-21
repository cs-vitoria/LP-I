package org.example;

public class Cabelo { //classe

    //atributos
    private String cor;
    private Double tamanho;
    private String textura;

    //construtor
    public  Cabelo(String cor, Double tamanho, String textura){ //parametros entre parenteses, diferente do atributo
        // o construtor obriga o usuário a colocar parametro quando criar o objeto
        this.cor = cor;
        this.tamanho = tamanho;
        this.textura = textura;
    }

    public String getCor(){return cor;}
    public Double getTamanho(){
        return tamanho;
    }
    public String getTextura(){
        return textura;
    }
    public void setCor(String cor){
        this.cor = cor;
    }
    public void setTamanho(Double tamanho){
        this.tamanho = tamanho;
    }
    public void setTextura (String textura){
        this.textura = textura;
    }

    String prender() {//metodo
        return("o seu cabelo " + textura +" "+ cor + " com " + tamanho + "" +
                "\n cm de comprimento está preso em um rabo de cavalo.");
    }
    String lavar() {
        return("o seu cabelo " + textura +" "+ cor + " com " + tamanho + "" +
                "\n cm de comprimento está limpo e cheiroso.");
    }
    String arrumar() {
        return("o seu cabelo " + textura +" "+ cor + " com " + tamanho + "" +
                "\n cm de comprimento está arrumado, bonito e elegante.");
    }
}
