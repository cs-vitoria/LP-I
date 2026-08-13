import java.util.Scanner;

public class Aluguel {
    void main(){
        int dias;
        double km, dif;
        Scanner s=new Scanner(System.in);
        IO.println("foram quantos dias de aluguel? ");
        dias = s.nextInt();
        IO.println("qual foi a distância percorrida (em km)? ");
        km = s.nextDouble();
        double taxa_fixa = 98.99;
        double add = 39.99;
        double diaria = dias * taxa_fixa;
        if (km > 150) dif = km - 150;
        else dif = 0;
        double valor_final = diaria + (dif * add);
        IO.println("o aluguel foi de "+dias+" dias, e foram percorridos "+km+" quilômetros com o veículo." +
                "\no total que deve ser pago é de R$ "+valor_final);
    }
}
