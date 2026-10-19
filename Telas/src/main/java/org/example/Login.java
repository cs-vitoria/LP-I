package org.example;

import java.util.Objects;
import java.util.Scanner;
public class Login {
    String entrar_senha(String senha_dig){
        String senha = "1234";
        //String senha_dig;
        //Scanner s = new Scanner (System.in);
        //System.out.println("digite sua senha: ");
        //senha_dig = s.next();//lê string, se for inteiro, precisa ser senha = s.nextInt();
        if (Objects.equals(senha_dig, senha)) return ("acesso permitido!");
        else return ("acesso negado.");

    }
}
