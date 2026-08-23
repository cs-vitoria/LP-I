import java.util.Scanner;

public class Viagem {
    void viajar(){
      double distancia, preco, desconto;
      Scanner s = new Scanner(System.in);
      IO.println("qual é a distância da viagem (em km)?");
      distancia = s.nextDouble();
      IO.println("qual é o preço do combustível (em L)?");
      preco = s.nextDouble();
      double custo = (distancia/12)*preco;
      if (distancia > 500) desconto = 0.05;
      else desconto = 0;
      double valor_final = custo - (1-desconto);
      IO.println("o custo para a viagem é de R$ "+valor_final);

    }
}
