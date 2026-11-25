package org.example.entidades;

public class Cabelo { //classe

    //atributos
    private int id;
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

    // construtor usado quando o objeto vem do banco (já possui id)
    public Cabelo(int id, String cor, Double tamanho, String textura) {
        this(cor, tamanho, textura);
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public String prender() {//metodo
        return("o seu cabelo " + textura +" "+ cor + " com " + tamanho + "" +
                "\n cm de comprimento está preso em um rabo de cavalo.");
    }
    public String lavar() {
        return("o seu cabelo " + textura +" "+ cor + " com " + tamanho + "" +
                "\n cm de comprimento está limpo e cheiroso.");
    }
    public String arrumar() {
        return("o seu cabelo " + textura +" "+ cor + " com " + tamanho + "" +
                "\n cm de comprimento está arrumado, bonito e elegante.");
    }
}
