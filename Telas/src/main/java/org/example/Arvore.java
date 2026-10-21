package org.example;

public class Arvore {
    private String folhagem;
    private String especie;
    private int idade;

    public Arvore (String folhagem, String especie, int idade){
        this.folhagem = folhagem;
        this.especie = especie;
        this.idade = idade;
    }

    public String getFolhagem(){
        return folhagem;
    }
    public String getEspecie(){
        return especie;
    }
    public int getIdade(){
        return idade;
    }
    public void setFolhagem(String folhagem){
        this.folhagem = folhagem;
    }
    public void setEspecie(String especie){
        this.especie = especie;
    }
    public void setIdade (int idade){
        this.idade = idade;
    }

    String cair(){
        return("Por conta da idade, a árvore de " +
                "\n " + idade + " anos caiu.");
    }
    String crescer(){
        return("A árvore da espécie " + especie + "" +
                "\n  está crescendo no quintal.");
    }
    String florescer(){
       return("A folhagem do tipo " + folhagem + ", " +
               "\n da árvore " + especie + ", está aumentando.");
    }
}
