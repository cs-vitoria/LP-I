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

    void cair(){
        IO.println("Por conta da idade, a árvore de " + idade + " anos caiu.");
    }
    void crescer(){
        IO.println("A árvore da espécie " + especie + " está crescendo no quintal.");
    }
    void florescer(){
        IO.println("A folhagem do tipo " + folhagem + ", da árvore " + especie + ", está aumentando.");
    }
}
