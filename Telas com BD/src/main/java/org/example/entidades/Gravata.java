package org.example.entidades;

public class Gravata {
    private int id;
    private String textura;
    private String estampa;
    private String tamanho;
    public Gravata(String textura, String estampa, String tamanho) {
        this.textura = textura;
        this.estampa = estampa;
        this.tamanho = tamanho;
    }

    // construtor usado quando o objeto vem do banco (já possui id)
    public Gravata(int id, String textura, String estampa, String tamanho) {
        this(textura, estampa, tamanho);
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTextura(){
        return textura;
    }
    public String getEstampa(){
        return estampa;
    }
    public String getTamanho(){
        return tamanho;
    }
    public void setTecido (String textura){
        this.textura = textura;
    }
    public void setTextura(String textura){
        this.textura = textura;
    }
    public void setEstampa(String estampa){
        this.estampa = estampa;
    }
    public void setTamanho(String tamanho){
        this.tamanho = tamanho;
    }

    public String amarrar() {
        return("Sua gravata " + textura + " tamanho " + tamanho + "," +
                "\n  com estampa " + estampa + " está amarrada.");
    }
    public String cortar() {
        return("Sua gravata " + textura + " tamanho " + tamanho + ", com estampa " +
                "\n " + estampa + " foi cortada para vestir melhor.");
    }
    public String pendurar() {
        return("Sua gravata " + textura + " tamanho " + tamanho + ", com " +
                "\n estampa " + estampa + " está pendurada no cabide.");
    }
}
