package org.example;

public class Mascara {
    private String material;
    private String face;
    private String estilo;

    public Mascara(String material, String face, String estilo){
        this.material = material;
        this.face = face;
        this.estilo = estilo;
    }
     public String getMaterial(){
        return material;
     }
     public String getFace(){
        return face;
     }
     public String getEstilo(){
        return estilo;
     }
     public void setMaterial(String material){
        this.material = material;
     }
     public void setFace(String face){
        this.face = face;
     }
     public void setEstilo(String estilo){
        this.estilo = estilo;
     }

    String pendurar(){
        return("A máscara de " + material + " " +
                "\n está pendurada.");
     }
    String produzir(){
        return("A artista precisa produzir uma máscara " +
                "\n do estilo " + estilo + " para um evento.");
     }
    String restaurar(){
        return("A máscara de face " + face + " " +
                "\n está sendo restaurada.");
     }
}
