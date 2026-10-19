package org.example;

import java.util.Scanner;

public class Viagem {
    String viagem(double distancia, double preco){
      double desconto;
      //Scanner s = new Scanner(System.in);
      //System.out.println("qual é a distância da viagem (em km)?");
      //distancia = s.nextDouble();
      //System.out.println("qual é o preço do combustível (em L)?");
      //preco = s.nextDouble();
      double custo = (distancia/12)*preco;
      if (distancia > 500) desconto = 0.05;
      else desconto = 0;
      double valor_final = custo - (1-desconto);
      return("o custo para a viagem é de R$ "+valor_final);
    }
}
