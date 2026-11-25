package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.daos.RostoDAO;
import org.example.entidades.Rosto;
import org.example.util.Dialogos;
import org.example.util.Validacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RostoController {

    @FXML
    private Button btn_hidratar;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button bt_limpar;

    @FXML
    private Button btn_maquiar;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_contraste;

    @FXML
    private TextField txt_cor;

    @FXML
    private TextField txt_formato;

    @FXML
    private Label txt_resultado;

    @FXML
    private Button bt_criar;

    private Rosto r;

    @FXML
    private TextField txt_id;

    @FXML
    private Button btn_editar;

    @FXML
    private Button btn_deletar;

    @FXML
    private Button btn_ver_todos;

    private final RostoDAO dao = new RostoDAO();

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
            if (Validacao.todosVazios(txt_cor, txt_contraste, txt_formato)) {
                txt_id.setText(String.valueOf(dao.proximoId()));
                txt_resultado.setText("");
                Dialogos.info("Criar rosto",
                        "Preencha os campos para criar um novo rosto.\nO ID do novo registro será " + txt_id.getText() + ".");
                return;
            }

            String cor = Validacao.texto(txt_cor, "Cor dos olhos", 50);
            String contraste = Validacao.texto(txt_contraste, "Contraste", 5);
            String formato = Validacao.texto(txt_formato, "Formato", 50);

            r = new Rosto(cor, contraste, formato);
            dao.inserir(r); // grava no banco e preenche o id gerado
            txt_id.setText(String.valueOf(r.getId()));
            txt_resultado.setText("");

            Dialogos.info("Sucesso", "Rosto criado com sucesso!\nID do registro: " + r.getId());
        } catch (IllegalArgumentException e) {
            Dialogos.erro(e.getMessage());
        } catch (SQLException e) {
            Dialogos.erroBanco(e);
        }
    }

    @FXML
    void hidratar(ActionEvent event) {
        txt_resultado.setText((r.hidratar()));
    }

    @FXML
    void limpar(ActionEvent event) {
        txt_id.clear();
        modo = Modo.NENHUM;
        txt_cor.clear();
        txt_contraste.clear();
        txt_formato.clear();
        txt_resultado.setText("");
    }

    @FXML
    void maquiar(ActionEvent event) {
        txt_resultado.setText((r.maquiar()));
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("menu");
    }
    @FXML
    void limpar_rosto(ActionEvent event) {
        txt_resultado.setText((r.limpar_rosto()));
    }

    @FXML
    void editar(ActionEvent event) {
        try {
            if (modo != Modo.EDITANDO) {
                // 1º clique: pede o id do registro e carrega os dados nos campos
                Integer id = Dialogos.pedirId("Editar rosto", "Digite o ID do rosto que deseja editar:");
                if (id == null) {
                    return;
                }
                Rosto encontrado = dao.buscarPorId(id);
                if (encontrado == null) {
                    Dialogos.erro("Não existe rosto com o ID " + id + ".");
                    return;
                }
                r = encontrado;
                idEmAcao = id;
                preencherCampos(r);
                modo = Modo.EDITANDO;
                txt_resultado.setText("Editando o registro de ID " + id + ".\nAltere os campos e clique em Editar novamente para salvar.");
                return;
            }

            // 2º clique: valida, pede confirmação e grava
            String cor = Validacao.texto(txt_cor, "Cor dos olhos", 50);
            String contraste = Validacao.texto(txt_contraste, "Contraste", 5);
            String formato = Validacao.texto(txt_formato, "Formato", 50);

            if (!Dialogos.confirmar("Confirmar edição",
                    "Deseja realmente salvar as alterações do registro de ID " + idEmAcao + "?")) {
                return;
            }

            Rosto atualizado = new Rosto(idEmAcao, cor, contraste, formato);
            if (!dao.atualizar(atualizado)) {
                Dialogos.erro("O registro de ID " + idEmAcao + " não existe mais.");
                return;
            }
            r = atualizado;
            modo = Modo.NENHUM;
            txt_resultado.setText("");
            Dialogos.info("Sucesso", "Rosto editado com sucesso!");
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
                Integer id = Dialogos.pedirId("Deletar rosto", "Digite o ID do rosto que deseja deletar:");
                if (id == null) {
                    return;
                }
                Rosto encontrado = dao.buscarPorId(id);
                if (encontrado == null) {
                    Dialogos.erro("Não existe rosto com o ID " + id + ".");
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
                Dialogos.info("Sucesso", "Rosto deletado com sucesso!");
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
            List<Rosto> registros = dao.listarTodos();
            Dialogos.verTodos("Todos os rostos", registros,
                new Dialogos.Coluna<Rosto>("ID", x -> String.valueOf(x.getId())),
                new Dialogos.Coluna<Rosto>("Cor dos olhos", x -> String.valueOf(x.getCor())),
                new Dialogos.Coluna<Rosto>("Contraste", x -> String.valueOf(x.getContraste())),
                new Dialogos.Coluna<Rosto>("Formato", x -> String.valueOf(x.getFormato())));
        } catch (SQLException e) {
            Dialogos.erroBanco(e);
        }
    }

    private void preencherCampos(Rosto r) {
        txt_id.setText(String.valueOf(r.getId()));
        txt_cor.setText(r.getCor());
        txt_contraste.setText(r.getContraste());
        txt_formato.setText(r.getFormato());
    }

    private void limparCampos() {
        txt_id.clear();
        txt_cor.clear();
        txt_contraste.clear();
        txt_formato.clear();
        txt_resultado.setText("");
    }

}
