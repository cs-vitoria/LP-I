package org.example;

public class Colecao {
    private String tipo;
    private int quantidade;
    private double valor;

    public Colecao(String tipo, int quantidade, double valor){
       this.tipo = tipo;
       this.quantidade = quantidade;
       this.valor = valor;
    }

    public String getTipo(){
        return tipo;
    }
    public int getQuantidade(){
        return quantidade;
    }
    public Double getValor(){
        return valor;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }
    public void setValor(Double valor){
        this.valor = valor;
    }
    String adicionar(){
        quantidade = quantidade + 1;
        return(quantidade + " item(ns) adicionado(s) à coleção de " + tipo);
    }
    String retirar(){
        quantidade = quantidade - 1;
        return(quantidade + " item(ns) retirado(s) da coleção de " + tipo);
    }
    String guardar(){
        return("A coleção de " + tipo + " está guardada porque vale " +
                "\n R$ " + valor + " e não pode ficar exposta.");
    }
}
