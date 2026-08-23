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

     void pendurar(){
        IO.println("A máscara de " + material + " está pendurada.");
     }
     void produzir(){
        IO.println("A artista precisa produzir uma máscara do estilo " + estilo + " para um evento.");
     }
     void restaurar(){
        IO.println("A máscara de face " + face + " está sendo restaurada.");
     }
}
