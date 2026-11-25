package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.daos.CabeloDAO;
import org.example.entidades.Cabelo;
import org.example.util.Dialogos;
import org.example.util.Validacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CabeloController {

    @FXML
    private Button btn_arrumar;

    @FXML
    private Button btn_lavar;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_prender;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_cor;

    @FXML
    private TextField txt_tamanho;

    @FXML
    private TextField txt_textura;

    @FXML
    private Label txt_resultado;

    @FXML
    private Button btn_criar;

    private Cabelo c;

    @FXML
    private TextField txt_id;

    @FXML
    private Button btn_editar;

    @FXML
    private Button btn_deletar;

    @FXML
    private Button btn_ver_todos;

    private final CabeloDAO dao = new CabeloDAO();

    // controla o fluxo de "clicar duas vezes": 1º clique pede o id, 2º clique confirma a ação
    private enum Modo { NENHUM, EDITANDO, DELETANDO }
    private Modo modo = Modo.NENHUM;
    private int idEmAcao;

    @FXML
    public void initialize() {
        txt_id.setEditable(false); // o id vem sempre do banco
    }

    @FXML
    void criar(ActionEvent event) {
        modo = Modo.NENHUM;
        try {
            // campos vazios: mostra o id que o novo registro vai receber e orienta o usuário
            if (Validacao.todosVazios(txt_cor, txt_tamanho, txt_textura)) {
                txt_id.setText(String.valueOf(dao.proximoId()));
                txt_resultado.setText("");
                Dialogos.info("Criar cabelo",
                        "Preencha os campos para criar um novo cabelo.\nO ID do novo registro será " + txt_id.getText() + ".");
                return;
            }

            String cor = Validacao.texto(txt_cor, "Cor", 50);
            double tamanho = Validacao.decimal(txt_tamanho, "Tamanho");
            String textura = Validacao.texto(txt_textura, "Textura", 50);

            c = new Cabelo(cor, tamanho, textura);
            dao.inserir(c); // grava no banco e preenche o id gerado
            txt_id.setText(String.valueOf(c.getId()));
            txt_resultado.setText("");

            Dialogos.info("Sucesso", "Cabelo criado com sucesso!\nID do registro: " + c.getId());
        } catch (IllegalArgumentException e) {
            Dialogos.erro(e.getMessage());
        } catch (SQLException e) {
            Dialogos.erroBanco(e);
        }
    }
    @FXML
    void arrumar(ActionEvent event) {
        txt_resultado.setText((c.arrumar()));
    }

    @FXML
    void lavar(ActionEvent event) {
        txt_resultado.setText((c.lavar()));
    }

    @FXML
    void limpar(ActionEvent event) {
        txt_id.clear();
        modo = Modo.NENHUM;
        txt_cor.clear();
        txt_tamanho.clear();
        txt_textura.clear();
        txt_resultado.setText("");
    }

    @FXML
    void prender(ActionEvent event) {
        txt_resultado.setText((c.prender()));
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("menu");
    }

    @FXML
    void editar(ActionEvent event) {
        try {
            if (modo != Modo.EDITANDO) {
                // 1º clique: pede o id do registro e carrega os dados nos campos
                Integer id = Dialogos.pedirId("Editar cabelo", "Digite o ID do cabelo que deseja editar:");
                if (id == null) {
                    return;
                }
                Cabelo encontrado = dao.buscarPorId(id);
                if (encontrado == null) {
                    Dialogos.erro("Não existe cabelo com o ID " + id + ".");
                    return;
                }
                c = encontrado;
                idEmAcao = id;
                preencherCampos(c);
                modo = Modo.EDITANDO;
                txt_resultado.setText("Editando o registro de ID " + id + ".\nAltere os campos e clique em Editar novamente para salvar.");
                return;
            }

            // 2º clique: valida, pede confirmação e grava
            String cor = Validacao.texto(txt_cor, "Cor", 50);
            double tamanho = Validacao.decimal(txt_tamanho, "Tamanho");
            String textura = Validacao.texto(txt_textura, "Textura", 50);

            if (!Dialogos.confirmar("Confirmar edição",
                    "Deseja realmente salvar as alterações do registro de ID " + idEmAcao + "?")) {
                return;
            }

            Cabelo atualizado = new Cabelo(idEmAcao, cor, tamanho, textura);
            if (!dao.atualizar(atualizado)) {
                Dialogos.erro("O registro de ID " + idEmAcao + " não existe mais.");
                return;
            }
            c = atualizado;
            modo = Modo.NENHUM;
            txt_resultado.setText("");
            Dialogos.info("Sucesso", "Cabelo editado com sucesso!");
        } catch (IllegalArgumentException e) {
            Dialogos.erro(e.getMessage());
        } catch (SQLException e) {
            Dialogos.erroBanco(e);
        }
    }

    @FXML
    void deletar(ActionEvent event) {
        try {
            if (modo != Modo.DELETANDO) {
                // 1º clique: pede o id do registro e mostra os dados que serão apagados
                Integer id = Dialogos.pedirId("Deletar cabelo", "Digite o ID do cabelo que deseja deletar:");
                if (id == null) {
                    return;
                }
                Cabelo encontrado = dao.buscarPorId(id);
                if (encontrado == null) {
                    Dialogos.erro("Não existe cabelo com o ID " + id + ".");
                    return;
                }
                idEmAcao = id;
                preencherCampos(encontrado);
                modo = Modo.DELETANDO;
                txt_resultado.setText("O registro de ID " + id + " será deletado.\nClique em Deletar novamente para confirmar.");
                return;
            }

            // 2º clique: pede confirmação e apaga
            if (!Dialogos.confirmar("Confirmar exclusão",
                    "Deseja realmente deletar o registro de ID " + idEmAcao + "?\nEsta ação não pode ser desfeita.")) {
                return;
            }

            boolean deletou = dao.deletar(idEmAcao);
            modo = Modo.NENHUM;
            limparCampos();
            if (deletou) {
                Dialogos.info("Sucesso", "Cabelo deletado com sucesso!");
            } else {
                Dialogos.erro("O registro de ID " + idEmAcao + " não existe mais.");
            }
        } catch (SQLException e) {
            Dialogos.erroBanco(e);
        }
    }

    @FXML
    void verTodos(ActionEvent event) {
        try {
            List<Cabelo> registros = dao.listarTodos();
            Dialogos.verTodos("Todos os cabelos", registros,
                new Dialogos.Coluna<Cabelo>("ID", x -> String.valueOf(x.getId())),
                new Dialogos.Coluna<Cabelo>("Cor", x -> String.valueOf(x.getCor())),
                new Dialogos.Coluna<Cabelo>("Tamanho", x -> String.valueOf(x.getTamanho())),
                new Dialogos.Coluna<Cabelo>("Textura", x -> String.valueOf(x.getTextura())));
        } catch (SQLException e) {
            Dialogos.erroBanco(e);
        }
    }

    private void preencherCampos(Cabelo c) {
        txt_id.setText(String.valueOf(c.getId()));
        txt_cor.setText(c.getCor());
        txt_tamanho.setText(String.valueOf(c.getTamanho()));
        txt_textura.setText(c.getTextura());
    }

    private void limparCampos() {
        txt_id.clear();
        txt_cor.clear();
        txt_tamanho.clear();
        txt_textura.clear();
        txt_resultado.setText("");
    }

}
