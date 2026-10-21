package org.example;

public class Terno {
    private String cor;
    private String tamanho;
    private String tecido;

    public Terno(String cor, String tamanho, String tecido) {
        this.cor = cor;
        this.tamanho = tamanho;
        this.tecido = tecido;
    }
    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    public String getTamanho() {
        return tamanho;
    }
    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
    public String getTecido() {
        return tecido;
    }
    public void setTecido(String tecido) {
        this.tecido = tecido;
    }

    String rasgar () {
            return("Seu terno " + cor + ", tamanho " + tamanho + ", " +
                    "\n feito de " + tecido + " está rasgado.");}
    String sujar () {
            return("Seu terno " + cor +  " , tamanho " + tamanho + ", " +
                    "\n feito de " + tecido + " está sujo.");
        }
    String passar () {
            return("Seu terno " + cor + ", tamanho " + tamanho + ", feito de " +
                    "\n " + tecido + " está passado e pendurado no cabide.");
        }
    }

