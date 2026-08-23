public class Bicicleta {
    private String cor;
    private String modelo;
    private String marca;

    public Bicicleta(String cor, String modelo, String marca){
        this.cor = cor;
        this.modelo = modelo;
        this.marca = marca;
    }

    public String getCor(){
        return cor;
    }
    public String getModelo(){
        return modelo;
    }
    public String getMarca(){
        return marca;
    }
    public void setCor(String cor){
        this.cor = cor;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }

    void quebrar(){
        IO.println("A bicicleta " + modelo + " " + cor + ", da marca " + marca + ", está quebrada e com peças faltando.");
    }
    void andar(){
        IO.println("A bicicleta " + modelo + " " + cor + ", da marca " + marca + ", está em movimento.");
    }
    void freiar(){
        IO.println("A bicicleta " + modelo + " " + cor + ", da marca " + marca + ", está parada.");
    }
}
