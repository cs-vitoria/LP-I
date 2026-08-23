import java.util.Scanner;

public class Main {
    //atributos ficam aqui, entre a classe e o metodo; são reconhecidos em toda a classe (global)
    void main() {//metodo
        int escolha; //não é atributo, é apenas uma variável local do metodo
        IO.println("Qual opção você deseja executar?\n" +
                "1 - Verificação de Múltiplo\n" +
                "2 - Sistema de Login Simplificado\n" +
                "3 - Compra com Desconto\n" +
                "4 - Temperatura\n" +
                "5 - Os Trinta e Cinco Camelos\n" +
                "6 - Calculadora de Viagem\n" +
                "7 - Aluguel de Carro");
        Scanner s = new Scanner(System.in);
        escolha = s.nextInt();

        if (escolha == 1) {
            Multiplo a = new Multiplo(); //declaração e instanciação de objeto
            //declarar -  cria o nome e o tipo do objeto
            //instanciar usa a palavra new para criar o objeto na memória
            //antes do igual só declaramos, depois do igual criamos o objeto
            a.multi(); //
        }
        if (escolha == 2) {
            Login a = new Login(); //objeto é uma "variável" associada a uma classe
            a.senha();
        }
        if (escolha == 3) {
            Compra a = new Compra();
            a.compra();
        }
        if (escolha == 4) {
            Temperatura a = new Temperatura();
            a.temp();
        }
        if (escolha == 5) {
            Camelo a = new Camelo();
            a.camelos();
        }
        if (escolha == 6) {
            Viagem a = new Viagem();
            a.viajar();
        }
        if (escolha == 7) {
            Aluguel a = new Aluguel();
            a.alugar();
        }
    }
}