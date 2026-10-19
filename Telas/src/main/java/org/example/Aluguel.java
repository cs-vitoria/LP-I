package org.example;

import java.util.Scanner;

public class Aluguel {
    String alugar(int dias, double km){
        //int dias;
        double dif;
        //Scanner s=new Scanner(System.in);
        //System.out.println("foram quantos dias de aluguel? ");
        //dias = s.nextInt();
        //System.out.println("qual foi a distância percorrida (em km)? ");
        //km = s.nextDouble();
        double taxa_fixa = 98.99;
        double add = 39.99;
        double diaria = dias * taxa_fixa;
        if (km > 150) dif = km - 150;
        else dif = 0;
        double valor_final = diaria + (dif * add);
        return("o aluguel foi de "+dias+" dias, " +
                "\ne foram percorridos "+km+" quilômetros com o veículo." +
                "\no total que deve ser pago é de R$ "+valor_final);
    }
}
