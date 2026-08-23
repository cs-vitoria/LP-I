import java.util.Scanner;
public class Login {
    void senha(){
        String senha = "1234";
        String senha_dig;
        Scanner s = new Scanner (System.in);
        IO.println("digite sua senha: ");
        senha_dig = s.next();//lê string, se for inteiro, precisa ser senha = s.nextInt();
        if (senha_dig == senha) IO.println ("acesso permitido!");
        else IO.println ("acesso negado.");

    }
}
