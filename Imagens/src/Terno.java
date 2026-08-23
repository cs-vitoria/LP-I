public class Terno {
    private String cor;
    private String tamanho;
    private String tecido;

    public Terno(String cor, String tamanho, String tecido) {
        this.cor = cor;
        this.tamanho = tamanho;
        this.tecido = tecido;
    }
    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    public String getTamanho() {
        return tamanho;
    }
    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
    public String getTecido() {
        return tecido;
    }
    public void setTecido(String tecido) {
        this.tecido = tecido;
    }

        void rasgar () {
            IO.println("Seu terno " + cor + ", tamanho " + tamanho + ", feito de " + tecido + " está rasgado.");
        }
        void sujar () {
            IO.println("Seu terno " + cor +  " , tamanho " + tamanho + ", feito de " + tecido + " está sujo.");
        }
        void passar () {
            IO.println("Seu terno " + cor + ", tamanho " + tamanho + ", feito de " + tecido + " está passado e pendurado no cabide.");
        }
    }

