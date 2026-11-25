package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.daos.BicicletaDAO;
import org.example.entidades.Bicicleta;
import org.example.util.Dialogos;
import org.example.util.Validacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BicicletaController {

    @FXML
    private Button btn_andar;

    @FXML
    private Button btn_freiar;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_quebrar;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_cor;

    @FXML
    private TextField txt_marca;

    @FXML
    private TextField txt_modelo;

    @FXML
    private Label txt_resultado;
    @FXML
    private Button btn_criar;

    private Bicicleta b;

    @FXML
    private TextField txt_id;

    @FXML
    private Button btn_editar;

    @FXML
    private Button btn_deletar;

    @FXML
    private Button btn_ver_todos;

    private final BicicletaDAO dao = new BicicletaDAO();

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
            if (Validacao.todosVazios(txt_cor, txt_modelo, txt_marca)) {
                txt_id.setText(String.valueOf(dao.proximoId()));
                txt_resultado.setText("");
                Dialogos.info("Criar bicicleta",
                        "Preencha os campos para criar uma nova bicicleta.\nO ID do novo registro será " + txt_id.getText() + ".");
                return;
            }

            String cor = Validacao.texto(txt_cor, "Cor", 50);
            String modelo = Validacao.texto(txt_modelo, "Modelo", 50);
            String marca = Validacao.texto(txt_marca, "Marca", 50);

            b = new Bicicleta(cor, modelo, marca);
            dao.inserir(b); // grava no banco e preenche o id gerado
            txt_id.setText(String.valueOf(b.getId()));
            txt_resultado.setText("");

            Dialogos.info("Sucesso", "Bicicleta criada com sucesso!\nID do registro: " + b.getId());
        } catch (IllegalArgumentException e) {
            Dialogos.erro(e.getMessage());
        } catch (SQLException e) {
            Dialogos.erroBanco(e);
        }
    }


    @FXML
    void andar(ActionEvent event) {
txt_resultado.setText(b.andar());
    }

    @FXML
    void freiar(ActionEvent event) {
        txt_resultado.setText(b.freiar());
    }

    @FXML
    void limpar(ActionEvent event) {
        txt_id.clear();
        modo = Modo.NENHUM;
        txt_cor.clear();
        txt_marca.clear();
        txt_modelo.clear();
        txt_resultado.setText("");
    }

    @FXML
    void quebrar(ActionEvent event) {
        txt_resultado.setText(b.quebrar());
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
                Integer id = Dialogos.pedirId("Editar bicicleta", "Digite o ID da bicicleta que deseja editar:");
                if (id == null) {
                    return;
                }
                Bicicleta encontrada = dao.buscarPorId(id);
                if (encontrada == null) {
                    Dialogos.erro("Não existe bicicleta com o ID " + id + ".");
                    return;
                }
                b = encontrada;
                idEmAcao = id;
                preencherCampos(b);
                modo = Modo.EDITANDO;
                txt_resultado.setText("Editando o registro de ID " + id + ".\nAltere os campos e clique em Editar novamente para salvar.");
                return;
            }

            // 2º clique: valida, pede confirmação e grava
            String cor = Validacao.texto(txt_cor, "Cor", 50);
            String modelo = Validacao.texto(txt_modelo, "Modelo", 50);
            String marca = Validacao.texto(txt_marca, "Marca", 50);

            if (!Dialogos.confirmar("Confirmar edição",
                    "Deseja realmente salvar as alterações do registro de ID " + idEmAcao + "?")) {
                return;
            }

            Bicicleta atualizada = new Bicicleta(idEmAcao, cor, modelo, marca);
            if (!dao.atualizar(atualizada)) {
                Dialogos.erro("O registro de ID " + idEmAcao + " não existe mais.");
                return;
            }
            b = atualizada;
            modo = Modo.NENHUM;
            txt_resultado.setText("");
            Dialogos.info("Sucesso", "Bicicleta editada com sucesso!");
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
                Integer id = Dialogos.pedirId("Deletar bicicleta", "Digite o ID da bicicleta que deseja deletar:");
                if (id == null) {
                    return;
                }
                Bicicleta encontrada = dao.buscarPorId(id);
                if (encontrada == null) {
                    Dialogos.erro("Não existe bicicleta com o ID " + id + ".");
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
                Dialogos.info("Sucesso", "Bicicleta deletada com sucesso!");
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
            List<Bicicleta> registros = dao.listarTodos();
            Dialogos.verTodos("Todas as bicicletas", registros,
                new Dialogos.Coluna<Bicicleta>("ID", x -> String.valueOf(x.getId())),
                new Dialogos.Coluna<Bicicleta>("Cor", x -> String.valueOf(x.getCor())),
                new Dialogos.Coluna<Bicicleta>("Modelo", x -> String.valueOf(x.getModelo())),
                new Dialogos.Coluna<Bicicleta>("Marca", x -> String.valueOf(x.getMarca())));
        } catch (SQLException e) {
            Dialogos.erroBanco(e);
        }
    }

    private void preencherCampos(Bicicleta b) {
        txt_id.setText(String.valueOf(b.getId()));
        txt_cor.setText(b.getCor());
        txt_modelo.setText(b.getModelo());
        txt_marca.setText(b.getMarca());
    }

    private void limparCampos() {
        txt_id.clear();
        txt_cor.clear();
        txt_modelo.clear();
        txt_marca.clear();
        txt_resultado.setText("");
    }

}
