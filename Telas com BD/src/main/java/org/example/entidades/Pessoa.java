package org.example.entidades;

public class Pessoa {

    private int id;
    private String nome;
    private int idade;
    private Double altura;

    public Pessoa (String nome, int idade, Double altura){
        this.nome = nome;
        this.idade = idade;
        this.altura = altura;
    }

    // construtor usado quando o objeto vem do banco (já possui id)
    public Pessoa(int id, String nome, int idade, Double altura) {
        this(nome, idade, altura);
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome(){
        return nome;
    }
    public int getIdade(){
        return idade;
    }
    public Double getAltura(){
        return altura;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setIdade(int idade){
        this.idade = idade;
    }
    public void setAltura(Double altura){
        this.altura = altura;
    }

    public String correr(){
        return(nome + ", que tem " + idade + "" +
                "\n  anos e mede " + altura + " m, está correndo.");
    }
    public String pedalar(){
        return(nome + ", que tem " + idade + " anos e mede " +
                "\n " + altura + " m, está andando de bicicleta.");
    }
    public String gritar(){
        return(nome + ", que tem " + idade + " anos e mede " +
                "\n " + altura + " m, está gritando.");
    }
}
