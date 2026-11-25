package org.example.util;

import javafx.scene.control.TextField;

/**
 * Leitura e validação dos campos de texto das telas.
 * Quando o valor é inválido lança IllegalArgumentException com uma mensagem pronta para o usuário.
 */
public final class Validacao {

    private Validacao() {
    }

    /** true se TODOS os campos estiverem vazios (ou só com espaços). */
    public static boolean todosVazios(TextField... campos) {
        for (TextField campo : campos) {
            if (campo.getText() != null && !campo.getText().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static String texto(TextField campo, String nomeDoCampo) {
        String valor = campo.getText() == null ? "" : campo.getText().trim();
        if (valor.isEmpty()) {
            throw new IllegalArgumentException("Preencha o campo " + nomeDoCampo + ".");
        }
        return valor;
    }

    /** Igual a texto(), mas também confere o tamanho máximo da coluna VARCHAR do banco. */
    public static String texto(TextField campo, String nomeDoCampo, int maximo) {
        String valor = texto(campo, nomeDoCampo);
        if (valor.length() > maximo) {
            throw new IllegalArgumentException("O campo " + nomeDoCampo + " aceita no máximo " + maximo + " caracteres.");
        }
        return valor;
    }

    public static int inteiro(TextField campo, String nomeDoCampo) {
        String valor = texto(campo, nomeDoCampo);
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O campo " + nomeDoCampo + " deve ser um número inteiro.");
        }
    }

    /** Aceita vírgula ou ponto como separador decimal (1,75 ou 1.75). */
    public static double decimal(TextField campo, String nomeDoCampo) {
        String valor = texto(campo, nomeDoCampo).replace(',', '.');
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O campo " + nomeDoCampo + " deve ser um número (ex.: 1.75).");
        }
    }
}
