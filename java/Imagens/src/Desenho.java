public class Desenho {
    private String pintor;
    private String estilo;
    private String cor;

    public Desenho (String pintor, String estilo, String cor){
        this.pintor = pintor;
        this.estilo = estilo;
        this.cor = cor;
    }

    public String getAutor(){return pintor;}
    public String getEstilo(){return estilo;}
    public String getCor(){return cor;}
    public void setAutor(String pintor){this.pintor = pintor;}
    public void setEstilo(String estilo){this.estilo = estilo;}
    public void setCor(String cor){this.cor = cor;}

    void esbocar(){
        IO.println("Estou fazendo um esboço de desenho inspirado no(a) pintor(a) " + pintor);
    }
    void pintar(){
        IO.println("Pintei um desenho na cor " + cor);
    }
    void apagar(){
        IO.println("Apaguei o desenho que fiz no estilo " + estilo);
    }
}
