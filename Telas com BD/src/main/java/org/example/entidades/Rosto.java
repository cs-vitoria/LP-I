package org.example.entidades;

public class Rosto {

    private int id;
    private String cor_olho;
    private String contraste;
    private String formato;

    public Rosto(String cor_olho, String contraste, String formato){
        this.cor_olho = cor_olho;
        this.contraste = contraste;
        this.formato = formato;
    }

    // construtor usado quando o objeto vem do banco (já possui id)
    public Rosto(int id, String cor_olho, String contraste, String formato) {
        this(cor_olho, contraste, formato);
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCor(){return cor_olho;}
    public String getContraste(){return contraste;}
    public String getFormato(){return formato;}
    public void setCor(String cor_olho){this.cor_olho = cor_olho;}
    public void setContraste(String contraste){
        this.contraste = contraste;}
    public void setFormato(String formato){this.formato = formato;}

    public String limpar_rosto(){
        return("O rosto de contraste " + contraste + ", que tem o formato " +
                "\n " + formato + " e possui olhos " + cor_olho + " está limpo.");
    }
    public String hidratar(){
        return("O rosto de contraste " + contraste + ", que tem o formato " +
                "\n " + formato + " e possui olhos " + cor_olho + " está hidratado.");
    }
    public String maquiar(){
        return("O rosto de contraste " + contraste + ", que tem o formato " +
                "\n " + formato + " e possui olhos " + cor_olho + " está maquiado.");
    }
}
