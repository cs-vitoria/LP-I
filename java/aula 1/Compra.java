import java.util.Scanner;
public class Compra {
    public void main() {
        double arroz, feijao, oleo, acucar, cafe, mac, trigo, fuba, molho, sal, total, desconto, valor_final;

        Scanner s = new Scanner (System.in);
        IO.println("arroz tipo 1 5kg: ");
        arroz = s.nextDouble();
        IO.println("feijao carioca 1kg: ");
        feijao = s.nextDouble();
        IO.println("oleo de soja 900ml: ");
        oleo = s.nextDouble();
        IO.println("açúcar cristal 1kg: ");
        acucar = s.nextDouble();
        IO.println("café torrado 250g: ");
        cafe = s.nextDouble();
        IO.println("macarrão parafuso 500g: ");
        mac = s.nextDouble();
        IO.println("farinha de trigo 500g: ");
        trigo = s.nextDouble();
        IO.println("fubá de milho 500g: ");
        fuba = s.nextDouble();
        IO.println("molho de tomate: ");
        molho = s.nextDouble();
        IO.println("sal refinado 1kg: ");
        sal = s.nextDouble();
        total = arroz + feijao + oleo + acucar + cafe + mac + trigo + fuba + molho + sal;
        if (total > 100) desconto = total * 0.1;
        else desconto = 0;
        valor_final = total - desconto;
        IO.println("\ntotal da compra: "+ total+
        "\nvalor final da compra: "+ valor_final);
    }
}
