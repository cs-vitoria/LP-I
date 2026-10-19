package org.example;

public class Temperatura {

    public String media_temp(double jan, double fev, double mar, double abr, double mai, double jun,
                             double jul, double ago, double set, double out, double nov, double dez) {

        // Calcula a média direto
        double soma = (jan + fev + mar + abr + mai + jun + jul + ago + set + out + nov + dez);
        double media = soma / 12.0;

        String cond;
        if (media < 18) {
            cond = "frio";
        } else {
            cond = "temperatura agradável";
        }

        // Retorna a frase final usando o operador + para juntar os textos
        return "A temperatura média do ano em sjc foi " + String.format("%.2f", media) +
                "°C, sendo considerada " + cond;
    }
}