public class Camisa {
    private String cor;
    private String tamanho;
    private String tecido;

    public Camisa(String cor, String tamanho, String tecido){
        this.cor = cor;
        this.tamanho = tamanho;
        this.tecido = tecido;
    }
    public String getCor(){
        return cor;
    }
    public String getTamanho(){
        return tamanho;
    }
    public String getTecido(){
        return tecido;
    }
    public void setCor(String cor){
        this.cor = cor;
    }
    public void setTamanho(String tamanho){
        this.tamanho = tamanho;
    }
    public void setTecido(String tecido){
        this.tecido = tecido;
    }

    void lavar() {
        IO.println("Sua camisa "+ cor + " de tamanho " + tamanho+ ", feita de "+ tecido + ", está lavada.");
    }
    void costurar() {
        IO.println("Sua camisa "+ cor + " de tamanho " + tamanho+ ", feita de "+ tecido + ", está na costureira.");
    }
    void secar() {
        IO.println("Sua camisa "+ cor + " de tamanho " + tamanho+ ", feita de "+ tecido + ", está secando no varal.");
    }
}
