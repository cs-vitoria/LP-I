package org.example.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Pop-ups usados por todas as telas (criar, editar, deletar e ver todos).
 */
public final class Dialogos {

    private Dialogos() {
    }

    /** Pop-up de informação / sucesso. */
    public static void info(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    /** Pop-up de erro. */
    public static void erro(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    /** Pop-up de erro para falhas do banco de dados. */
    public static void erroBanco(SQLException e) {
        erro("Erro ao acessar o banco de dados:\n" + e.getMessage());
    }

    /** Pop-up de confirmação com botões Sim / Não. Devolve true se o usuário clicou em Sim. */
    public static boolean confirmar(String titulo, String mensagem) {
        ButtonType sim = new ButtonType("Sim", ButtonBar.ButtonData.YES);
        ButtonType nao = new ButtonType("Não", ButtonBar.ButtonData.NO);

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, mensagem, sim, nao);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);

        Optional<ButtonType> resposta = alerta.showAndWait();
        return resposta.isPresent() && resposta.get() == sim;
    }

    /**
     * Pop-up para o usuário digitar o id de um registro.
     * Devolve o id digitado, ou null se ele cancelou ou digitou algo inválido.
     */
    public static Integer pedirId(String titulo, String mensagem) {
        TextInputDialog dialogo = new TextInputDialog();
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(mensagem);
        dialogo.setContentText("ID:");

        Optional<String> resposta = dialogo.showAndWait();
        if (!resposta.isPresent()) {
            return null; // clicou em Cancelar
        }
        try {
            int id = Integer.parseInt(resposta.get().trim());
            if (id <= 0) {
                throw new NumberFormatException();
            }
            return id;
        } catch (NumberFormatException e) {
            erro("Digite um número inteiro maior que zero para o ID.");
            return null;
        }
    }

    /** Uma coluna da tabela do "Ver todos": título + como obter o valor a partir do objeto. */
    public static class Coluna<T> {
        private final String titulo;
        private final Function<T, String> valor;

        public Coluna(String titulo, Function<T, String> valor) {
            this.titulo = titulo;
            this.valor = valor;
        }
    }

    /** Pop-up só de consulta com todos os registros e todas as informações. */
    @SafeVarargs
    public static <T> void verTodos(String titulo, List<T> registros, Coluna<T>... colunas) {
        if (registros.isEmpty()) {
            info(titulo, "Nenhum registro cadastrado.");
            return;
        }

        TableView<T> tabela = new TableView<>();
        double larguraColuna = 500.0 / colunas.length;
        for (Coluna<T> coluna : colunas) {
            TableColumn<T, String> tc = new TableColumn<>(coluna.titulo);
            tc.setCellValueFactory(celula -> new SimpleStringProperty(coluna.valor.apply(celula.getValue())));
            tc.setPrefWidth(larguraColuna);
            tc.setSortable(false);
            tabela.getColumns().add(tc);
        }
        tabela.setItems(FXCollections.observableArrayList(registros));
        tabela.setPrefSize(520, 300);

        Dialog<Void> dialogo = new Dialog<>();
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(titulo + " (" + registros.size() + " registro(s))");
        dialogo.getDialogPane().setContent(tabela);
        dialogo.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialogo.setResizable(true);
        dialogo.showAndWait();
    }
}
