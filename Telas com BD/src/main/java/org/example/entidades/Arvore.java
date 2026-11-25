package org.example.entidades;

public class Arvore {
    private int id;
    private String folhagem;
    private String especie;
    private int idade;

    public Arvore (String folhagem, String especie, int idade){
        this.folhagem = folhagem;
        this.especie = especie;
        this.idade = idade;
    }

    // construtor usado quando o objeto vem do banco (já possui id)
    public Arvore(int id, String folhagem, String especie, int idade) {
        this(folhagem, especie, idade);
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public String cair(){
        return("Por conta da idade, a árvore de " +
                "\n " + idade + " anos caiu.");
    }
    public String crescer(){
        return("A árvore da espécie " + especie + "" +
                "\n  está crescendo no quintal.");
    }
    public String florescer(){
       return("A folhagem do tipo " + folhagem + ", " +
               "\n da árvore " + especie + ", está aumentando.");
    }
}
