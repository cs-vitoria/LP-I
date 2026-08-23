import java.util.Scanner;

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

    void prender() {//metodo
        IO.println("o seu cabelo " + textura +" "+ cor + " com " + tamanho + "cm de comprimento está preso em um rabo de cavalo.");
    }
    void lavar() {
        IO.println("o seu cabelo " + textura +" "+ cor + " com " + tamanho + "cm de comprimento está limpo e cheiroso.");
    }
    void arrumar() {
        IO.println("o seu cabelo " + textura +" "+ cor + " com " + tamanho + "cm de comprimento está arrumado, bonito e elegante.");
    }
}
