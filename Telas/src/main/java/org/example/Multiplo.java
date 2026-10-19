package org.example;

import java.util.Scanner;

public class Multiplo {
    String descobrir_multiplo(int num1, int num2){
        if (num1%num2 == 0) return (num1 + " é múltiplo de " + num2);
        return (num1 + " não é múltiplo de " + num2);
    }
}
