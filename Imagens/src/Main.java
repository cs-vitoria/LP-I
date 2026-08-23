import java.util.Scanner;

public class Main {
    void main() {
        String cor;
        String textura;
        double tamanho;
        Scanner s = new Scanner(System.in);

        IO.println("Qual opção você deseja executar? \n" +
                "1. Arvore\n" +
                "2. Bicicleta\n" +
                "3. Cabelo\n" +
                "4. Camisa\n" +
                "5. Colecao\n" +
                "6. Desenho\n" +
                "7. Exposicao\n" +
                "8. Gravata\n" +
                "9. Mascara\n" +
                "10. Pessoa\n" +
                "11. Rosto\n" +
                "12. Terno");

        int opcao;
        opcao = s.nextInt();
        if (opcao == 1) {
            Arvore a = new Arvore("composta", "Ipê amarelo", 13);
            a.florescer();
        }
        if (opcao == 2) {
            Bicicleta a = new Bicicleta ("rosa", "KWS", "Caloi");
            a.freiar();
        }
        if (opcao == 3) {
            IO.println("qual é a cor do seu cabelo? ");
            cor = s.next();
            IO.println("qual é o tamanho do seu cabelo? ");
            tamanho = s.nextDouble();
            IO.println("qual é a textura do seu cabelo? ");
            textura = s.next();
            Cabelo a = new Cabelo(cor, tamanho, textura);
            a.prender();
        }
        if (opcao == 4){
            Camisa a = new Camisa("azul", "M", "algodão");
            a.costurar();
        }
        if (opcao == 5){
            Colecao a = new Colecao("telas", 14, "25.000");
            a.guardar();
        }
        if (opcao == 6){
            Desenho a = new Desenho("Van Gogh", "impressionismo", "amarelo");
            a.esbocar();
        }
        if (opcao == 7){
            Exposicao a = new Exposicao(19, "Museu do Girassol", "esculturas");
            a.visitar();
        }
        if (opcao == 8){
            Gravata a = new Gravata("canelada", "de bolinha", "borboleta");
            a.amarrar();
        }
        if (opcao == 9){
            Mascara a = new Mascara("madeira", "listrada", "africana");
            a.pendurar();
        }
        if (opcao == 10){
            Pessoa a = new Pessoa("Vitória", 18, 1.65);
            a.pedalar();
        }
        if (opcao == 11){
            Rosto a = new Rosto("pretos", "alto", "alongado");
            a.hidratar();
        }
        if (opcao == 12){
            Terno a = new Terno ("marrom escuro", "G", "linho");
            a.rasgar();
        }

    }
}
