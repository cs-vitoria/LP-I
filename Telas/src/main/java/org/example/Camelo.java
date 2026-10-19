package org.example;

import java.util.Scanner;

public class Camelo {
    String calcular_camelos(int q) {
        int beremiz, hamed, harim, homem;
        //Scanner s = new Scanner(System.in);
        //System.out.println("qual é a quantidade de camelos? ");
        //q = s.nextInt();
        beremiz = q / 2;
        hamed = q / 3;
        harim = q / 9;
        homem = q - (beremiz + hamed + harim);
        return("\no irmão mais velho recebeu " + beremiz + " camelos" +
                "\no irmão do meio recebeu " + hamed + " camelos" +
                "\no irmão mais novo recebeu " + harim + " camelos" +
                "\no homem que contava recebeu "+ homem +" camelos");


    }
}
