public class Rosto {

    private String cor_olho;
    private String contraste;
    private String formato;

    public Rosto(String cor_olho, String contraste, String formato){
        this.cor_olho = cor_olho;
        this.contraste = contraste;
        this.formato = formato;
    }

    public String getCor(){return cor_olho;}
    public String getContraste(){return contraste;}
    public String getFormato(){return formato;}
    public void setCor(String cor_olho){cor_olho = cor_olho;}
    public void setContraste(String contraste){
        this.contraste = contraste;}
    public void setFormato(String formato){this.formato = formato;}

    void limpar(){
        IO.println("O rosto de contraste " + contraste + ", que tem o formato " + formato + " e possui olhos " + cor_olho + " está limpo.");
    }
    void hidratar(){
        IO.println("O rosto de contraste " + contraste + ", que tem o formato " + formato + " e possui olhos " + cor_olho + " está hidratado.");
    }
    void maquiar(){
        IO.println("O rosto de contraste " + contraste + ", que tem o formato " + formato + " e possui olhos " + cor_olho + " está maquiado.");
    }
}
