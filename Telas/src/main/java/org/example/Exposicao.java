package org.example;

public class Exposicao {
    private int dia;
    private String local;
    private String artes;

    public Exposicao(int dia, String local, String artes){
        this.dia = dia;
        this.local = local;
        this.artes = artes;
    }

    public int getDia(){
        return dia;
    }
    public String getLocal(){
        return local;
    }
    public String getArtes(){
        return artes;
    }
    public void setDia(int dia){
        this.dia = dia;
    }
    public void setLocal(String local){
        this.local = local;
    }
    public void setArtes(String artes){
        this.artes = artes;
    }

    String visitar(){
        return("Irei visitar uma exposição de" +
                "\n  " + artes + " no dia " + dia);
    }
    String organizar(){
        return("Estão organizando uma exposição de " + artes + "" +
                "\n  para o dia " + dia + ", e vai ser no " + local);
    }
    String observar(){
        return("Estou observando a exposição de " + artes);
    }
}
