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

    void visitar(){
        IO.println("Irei visitar uma exposição de " + artes + " no dia " + dia);
    }
    void organizar(){
        IO.println("Estão organizando uma exposição de " + artes + " para o dia " + dia + ", e vai ser no " + local);
    }
    void observar(){
        IO.println("Estou observando a exposição de " + artes);
    }
}
