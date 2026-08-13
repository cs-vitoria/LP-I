import java.util.Scanner;

public class Camelo {
    void main() {
        int camelo, beremiz, hamed, harim, homem;
        Scanner s = new Scanner(System.in);
        IO.println("qual é a quantidade de camelos? ");
        camelo = s.nextInt();
        beremiz = camelo / 2;
        hamed = camelo / 3;
        harim = camelo / 9;
        homem = camelo - (beremiz + hamed + harim);
        IO.println("\no irmão mais velho recebeu " + beremiz + " camelos" +
                "\no irmão do meio recebeu " + hamed + " camelos" +
                "\no irmão mais novo recebeu " + harim + " camelos" +
                "\no homem que contava recebeu "+ homem +" camelos");


    }
}
