package org.example;

public class Compra {

    public String calcular_compra(double arroz, double feijao, double oleo, double acucar, double cafe,
                                  double mac, double trigo, double fuba, double molho, double sal) {

        // Calcula o total dos itens
        double total = arroz + feijao + oleo + acucar + cafe + mac + trigo + fuba + molho + sal;

        // Calcula o desconto se o total for maior que 100
        double desconto;
        if (total > 100) {
            desconto = total * 0.1;
        } else {
            desconto = 0;
        }

        double valor_final = total - desconto;

        // Retorna a frase final com os valores formatados
        return "Total da compra: R$ " + String.format("%.2f", total) +
                "\nValor final com desconto: R$ " + String.format("%.2f", valor_final);
    }
}