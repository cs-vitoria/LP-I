public class Colecao {
    private String tipo;
    private int quantidade;
    private String valor;

    public Colecao(String tipo, int quantidade, String valor){
       this.tipo = tipo;
       this.quantidade = quantidade;
       this.valor = valor;
    }

    public String getTipo(){
        return tipo;
    }
    public int getQuantidade(){
        return quantidade;
    }
    public String getValor(){
        return valor;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }
    public void setValor(String valor){
        this.valor = valor;
    }
    void adicionar(){
        IO.println(quantidade + " item(ns) adicionado(s) à coleção de " + tipo);
    }
    void retirar(){
        IO.println(quantidade + " item(ns) retirado(s) da coleção de " + tipo);
    }
    void guardar(){
        IO.println("A coleção de " + tipo + " está guardada porque vale R$ " + valor + " e não pode ficar exposta.");
    }
}
