import java.util.Scanner;

public class Multiplo {
    void multi(){
        int num1;
        int num2;
        Scanner s=new Scanner(System.in);
        IO.println("digite o primeiro número: ");
        num1 = s.nextInt();
        IO.println("digite o segundo número: ");
        num2 = s.nextInt();
        if (num1%num2 == 0) IO.println("é múltiplo");
        else IO.println("não é múltiplo");
    }
}
