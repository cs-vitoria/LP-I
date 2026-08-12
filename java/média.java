public class Main {//classe
    double media; //atributo da classe, variavel global, sempre fora de método
    public static void main(String[] args) {
        // Basic math operations
       double p1, e1, e2, sub, x, api, exf, diferenca, base, parte1, notacalc, notafinal, parteapi;
        p1 = 4.0;
        e1 = 6.0;
        e2 = 7.5;
        sub = 10.0;
        x = 0.5;
        api = 8.7;
        exf = 0.0;
        base = (p1*0.5) + (e1*0.2) + (e2*0.3) + x + (sub*0.15);
        parte1 = base * 0.5;
        diferenca = base - 5.9;
        notafinal = 0.0;
        if(diferenca > 0) parteapi = (api * 0.5);
        else parteapi = 0;
        notacalc = parte1 + parteapi;
        if(notacalc >= 6) {notafinal=notacalc; 
        System.out.println ("aluno aprovado sem exf " + notafinal);
        }
        else{
            if (notacalc >= 4.0){
            exf = 8.0;
            notafinal = exf;
            System.out.println ("aluno aprovado com exf " + notafinal);
            }
            else System.out.println ("aluno aprovado " + notafinal);
        }
    }
}
