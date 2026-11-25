package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.daos.ArvoreDAO;
import org.example.entidades.Arvore;
import org.example.util.Dialogos;
import org.example.util.Validacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ArvoreController {

    @FXML
    private Button bt_criar;

    @FXML
    private Button bt_cair;

    @FXML
    private Button bt_crescer;

    @FXML
    private Button bt_florescer;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_especie;

    @FXML
    private TextField txt_folhagem;

    @FXML
    private TextField txt_idade;

    @FXML
    private Label txt_resultado;

    private Arvore a;

    @FXML
    private TextField txt_id;

    @FXML
    private Button bt_editar;

    @FXML
    private Button bt_deletar;

    @FXML
    private Button bt_ver_todos;

    private final ArvoreDAO dao = new ArvoreDAO();

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
            if (Validacao.todosVazios(txt_folhagem, txt_especie, txt_idade)) {
                txt_id.setText(String.valueOf(dao.proximoId()));
                txt_resultado.setText("");
                Dialogos.info("Criar árvore",
                        "Preencha os campos para criar uma nova árvore.\nO ID do novo registro será " + txt_id.getText() + ".");
                return;
            }

            String folhagem = Validacao.texto(txt_folhagem, "Folhagem", 50);
            String especie = Validacao.texto(txt_especie, "Espécie", 50);
            int idade = Validacao.inteiro(txt_idade, "Idade");

            a = new Arvore(folhagem, especie, idade);
            dao.inserir(a); // grava no banco e preenche o id gerado
            txt_id.setText(String.valueOf(a.getId()));
            txt_resultado.setText("");

            Dialogos.info("Sucesso", "Árvore criada com sucesso!\nID do registro: " + a.getId());
        } catch (IllegalArgumentException e) {
            Dialogos.erro(e.getMessage());
        } catch (SQLException e) {
            Dialogos.erroBanco(e);
        }
    }

    @FXML
    void cair(ActionEvent event) {
        txt_resultado.setText(a.cair());
    }

    @FXML
    void crescer(ActionEvent event) {
        txt_resultado.setText(a.crescer());
    }

    @FXML
    void florescer(ActionEvent event) {
        txt_resultado.setText(a.florescer());
    }

    @FXML
    void limpar(ActionEvent event) {
        txt_id.clear();
        modo = Modo.NENHUM;
    txt_folhagem.clear();
    txt_especie.clear();
    txt_idade.clear();
    txt_resultado.setText("");
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
                Integer id = Dialogos.pedirId("Editar árvore", "Digite o ID da árvore que deseja editar:");
                if (id == null) {
                    return;
                }
                Arvore encontrada = dao.buscarPorId(id);
                if (encontrada == null) {
                    Dialogos.erro("Não existe árvore com o ID " + id + ".");
                    return;
                }
                a = encontrada;
                idEmAcao = id;
                preencherCampos(a);
                modo = Modo.EDITANDO;
                txt_resultado.setText("Editando o registro de ID " + id + ".\nAltere os campos e clique em Editar novamente para salvar.");
                return;
            }

            // 2º clique: valida, pede confirmação e grava
            String folhagem = Validacao.texto(txt_folhagem, "Folhagem", 50);
            String especie = Validacao.texto(txt_especie, "Espécie", 50);
            int idade = Validacao.inteiro(txt_idade, "Idade");

            if (!Dialogos.confirmar("Confirmar edição",
                    "Deseja realmente salvar as alterações do registro de ID " + idEmAcao + "?")) {
                return;
            }

            Arvore atualizada = new Arvore(idEmAcao, folhagem, especie, idade);
            if (!dao.atualizar(atualizada)) {
                Dialogos.erro("O registro de ID " + idEmAcao + " não existe mais.");
                return;
            }
            a = atualizada;
            modo = Modo.NENHUM;
            txt_resultado.setText("");
            Dialogos.info("Sucesso", "Árvore editada com sucesso!");
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
                Integer id = Dialogos.pedirId("Deletar árvore", "Digite o ID da árvore que deseja deletar:");
                if (id == null) {
                    return;
                }
                Arvore encontrada = dao.buscarPorId(id);
                if (encontrada == null) {
                    Dialogos.erro("Não existe árvore com o ID " + id + ".");
                    return;
                }
                idEmAcao = id;
                preencherCampos(encontrada);
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
                Dialogos.info("Sucesso", "Árvore deletada com sucesso!");
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
            List<Arvore> registros = dao.listarTodos();
            Dialogos.verTodos("Todas as árvores", registros,
                new Dialogos.Coluna<Arvore>("ID", x -> String.valueOf(x.getId())),
                new Dialogos.Coluna<Arvore>("Folhagem", x -> String.valueOf(x.getFolhagem())),
                new Dialogos.Coluna<Arvore>("Espécie", x -> String.valueOf(x.getEspecie())),
                new Dialogos.Coluna<Arvore>("Idade", x -> String.valueOf(x.getIdade())));
        } catch (SQLException e) {
            Dialogos.erroBanco(e);
        }
    }

    private void preencherCampos(Arvore a) {
        txt_id.setText(String.valueOf(a.getId()));
        txt_folhagem.setText(a.getFolhagem());
        txt_especie.setText(a.getEspecie());
        txt_idade.setText(String.valueOf(a.getIdade()));
    }

    private void limparCampos() {
        txt_id.clear();
        txt_folhagem.clear();
        txt_especie.clear();
        txt_idade.clear();
        txt_resultado.setText("");
    }

}
