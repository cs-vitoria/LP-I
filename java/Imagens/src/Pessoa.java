public class Pessoa {

    private String nome;
    private int idade;
    private Double altura;

    public Pessoa (String nome, int idade, Double altura){
        this.nome = nome;
        this.idade = idade;
        this.altura = altura;
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

    void correr(){
        IO.println(nome + ", que tem " + idade + " anos e mede " + altura + " m, está correndo.");
    }
    void pedalar(){
        IO.println(nome + ", que tem " + idade + " anos e mede " + altura + " m, está andando de bicicleta.");
    }
    void gritar(){
        IO.println(nome + ", que tem " + idade + " anos e mede " + altura + " m, está gritando.");
    }
}
