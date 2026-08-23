public class Gravata {
    private String textura;
    private String estampa;
    private String modelo;
    public Gravata(String textura, String estampa, String modelo) {
        this.textura = textura;
        this.estampa = estampa;
        this.modelo = modelo;
    }
    public String getTextura(){
        return textura;
    }
    public String getEstampa(){
        return estampa;
    }
    public String getModelo(){
        return modelo;
    }
    public void setTecido (String textura){
        this.textura = textura;
    }
    public void setEstampa(String estampa){
        this.estampa = estampa;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    void amarrar() {
        IO.println("Sua gravata " + modelo + " " + textura + ", com estampa " + estampa + " está amarrada.");
    }
    void cortar() {
        IO.println("Sua gravata " + modelo + " " + textura + ", com estampa " + estampa + " foi cortada para vestir melhor.");
    }
    void pendurar() {
        IO.println("Sua gravata " + modelo + " " + textura + ", com estampa " + estampa + " está pendurada no cabide.");
    }
}
