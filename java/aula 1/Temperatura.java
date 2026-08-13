import java.util.ArrayList;
import java.util.List;

public class Temperatura {
    void main() {
        String cond;
        List<String> meses = new ArrayList<>();
        meses.addAll(List.of("janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outrubro", "novembro", "dezembro"));
        List<Integer> temperaturas = new ArrayList<>();
        temperaturas.addAll(List.of(24, 24, 24, 22, 19, 18, 18, 19, 20, 22, 23, 24));

        for (int i = 0; i < meses.size(); i++) {
            String mes = meses.get(i);
            double temp = temperaturas.get(i);

            if (temp < 18) cond = "frio";
            else cond = "agradável";
            IO.println("a temperatura em " + mes + " foi " + temp + "°C, sendo considerada " + cond);
        }

        int soma = (24 + 24 + 24 + 22 + 19 + 18 + 18 + 19 + 20 + 22 + 23 + 24);
        double media = soma / 12;

        if (media < 18) cond = "frio";
        else cond = "temperatura agradável";
        IO.println("\na temperatura média do ano em sjc foi " + media + "°C, sendo considerada " + cond);

    }
}