package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.daos.GravataDAO;
import org.example.entidades.Gravata;
import org.example.util.Dialogos;
import org.example.util.Validacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GravataController {

    @FXML
    private Button btn_amarrar;

    @FXML
    private Button btn_cortar;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_pendurar;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_estampa;

    @FXML
    private TextField txt_tamanho;

    @FXML
    private Label txt_resultado;

    @FXML
    private TextField txt_textura;

    @FXML
    private Button btn_criar;

    private Gravata g;

    @FXML
    private TextField txt_id;

    @FXML
    private Button btn_editar;

    @FXML
    private Button btn_deletar;

    @FXML
    private Button btn_ver_todos;

    private final GravataDAO dao = new GravataDAO();

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
            if (Validacao.todosVazios(txt_textura, txt_estampa, txt_tamanho)) {
                txt_id.setText(String.valueOf(dao.proximoId()));
                txt_resultado.setText("");
                Dialogos.info("Criar gravata",
                        "Preencha os campos para criar uma nova gravata.\nO ID do novo registro será " + txt_id.getText() + ".");
                return;
            }

            String textura = Validacao.texto(txt_textura, "Textura", 50);
            String estampa = Validacao.texto(txt_estampa, "Estampa", 50);
            String tamanho = Validacao.texto(txt_tamanho, "Tamanho", 5);

            g = new Gravata(textura, estampa, tamanho);
            dao.inserir(g); // grava no banco e preenche o id gerado
            txt_id.setText(String.valueOf(g.getId()));
            txt_resultado.setText("");

            Dialogos.info("Sucesso", "Gravata criada com sucesso!\nID do registro: " + g.getId());
        } catch (IllegalArgumentException e) {
            Dialogos.erro(e.getMessage());
        } catch (SQLException e) {
            Dialogos.erroBanco(e);
        }
    }

    @FXML
    void amarrar(ActionEvent event) {
        txt_resultado.setText((g.amarrar()));
    }

    @FXML
    void cortar(ActionEvent event) {
        txt_resultado.setText((g.cortar()));
    }

    @FXML
    void limpar(ActionEvent event) {
        txt_id.clear();
        modo = Modo.NENHUM;
        txt_textura.clear();
        txt_tamanho.clear();
        txt_estampa.clear();
        txt_resultado.setText("");
    }

    @FXML
    void pendurar(ActionEvent event) {
        txt_resultado.setText((g.pendurar()));
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
                Integer id = Dialogos.pedirId("Editar gravata", "Digite o ID da gravata que deseja editar:");
                if (id == null) {
                    return;
                }
                Gravata encontrada = dao.buscarPorId(id);
                if (encontrada == null) {
                    Dialogos.erro("Não existe gravata com o ID " + id + ".");
                    return;
                }
                g = encontrada;
                idEmAcao = id;
                preencherCampos(g);
                modo = Modo.EDITANDO;
                txt_resultado.setText("Editando o registro de ID " + id + ".\nAltere os campos e clique em Editar novamente para salvar.");
                return;
            }

            // 2º clique: valida, pede confirmação e grava
            String textura = Validacao.texto(txt_textura, "Textura", 50);
            String estampa = Validacao.texto(txt_estampa, "Estampa", 50);
            String tamanho = Validacao.texto(txt_tamanho, "Tamanho", 5);

            if (!Dialogos.confirmar("Confirmar edição",
                    "Deseja realmente salvar as alterações do registro de ID " + idEmAcao + "?")) {
                return;
            }

            Gravata atualizada = new Gravata(idEmAcao, textura, estampa, tamanho);
            if (!dao.atualizar(atualizada)) {
                Dialogos.erro("O registro de ID " + idEmAcao + " não existe mais.");
                return;
            }
            g = atualizada;
            modo = Modo.NENHUM;
            txt_resultado.setText("");
            Dialogos.info("Sucesso", "Gravata editada com sucesso!");
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
                Integer id = Dialogos.pedirId("Deletar gravata", "Digite o ID da gravata que deseja deletar:");
                if (id == null) {
                    return;
                }
                Gravata encontrada = dao.buscarPorId(id);
                if (encontrada == null) {
                    Dialogos.erro("Não existe gravata com o ID " + id + ".");
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
                Dialogos.info("Sucesso", "Gravata deletada com sucesso!");
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
            List<Gravata> registros = dao.listarTodos();
            Dialogos.verTodos("Todas as gravatas", registros,
                new Dialogos.Coluna<Gravata>("ID", x -> String.valueOf(x.getId())),
                new Dialogos.Coluna<Gravata>("Textura", x -> String.valueOf(x.getTextura())),
                new Dialogos.Coluna<Gravata>("Estampa", x -> String.valueOf(x.getEstampa())),
                new Dialogos.Coluna<Gravata>("Tamanho", x -> String.valueOf(x.getTamanho())));
        } catch (SQLException e) {
            Dialogos.erroBanco(e);
        }
    }

    private void preencherCampos(Gravata g) {
        txt_id.setText(String.valueOf(g.getId()));
        txt_textura.setText(g.getTextura());
        txt_estampa.setText(g.getEstampa());
        txt_tamanho.setText(g.getTamanho());
    }

    private void limparCampos() {
        txt_id.clear();
        txt_textura.clear();
        txt_estampa.clear();
        txt_tamanho.clear();
        txt_resultado.setText("");
    }

}
