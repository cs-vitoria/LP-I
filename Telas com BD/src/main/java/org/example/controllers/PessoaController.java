package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.daos.PessoaDAO;
import org.example.entidades.Pessoa;
import org.example.util.Dialogos;
import org.example.util.Validacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PessoaController {

    @FXML
    private Button btn_correr;

    @FXML
    private Button btn_gritar;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_pedalar;

    @FXML
    private Button btn_voltar;

    @FXML
    private TextField txt_altura;

    @FXML
    private TextField txt_idade;

    @FXML
    private TextField txt_nome;

    @FXML
    private Label txt_resultado;

    @FXML
    private Button btn_criar;

    private Pessoa p;

    @FXML
    private TextField txt_id;

    @FXML
    private Button btn_editar;

    @FXML
    private Button btn_deletar;

    @FXML
    private Button btn_ver_todos;

    private final PessoaDAO dao = new PessoaDAO();

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
            if (Validacao.todosVazios(txt_nome, txt_idade, txt_altura)) {
                txt_id.setText(String.valueOf(dao.proximoId()));
                txt_resultado.setText("");
                Dialogos.info("Criar pessoa",
                        "Preencha os campos para criar uma nova pessoa.\nO ID do novo registro será " + txt_id.getText() + ".");
                return;
            }

            String nome = Validacao.texto(txt_nome, "Nome", 100);
            int idade = Validacao.inteiro(txt_idade, "Idade");
            double altura = Validacao.decimal(txt_altura, "Altura");

            p = new Pessoa(nome, idade, altura);
            dao.inserir(p); // grava no banco e preenche o id gerado
            txt_id.setText(String.valueOf(p.getId()));
            txt_resultado.setText("");

            Dialogos.info("Sucesso", "Pessoa criada com sucesso!\nID do registro: " + p.getId());
        } catch (IllegalArgumentException e) {
            Dialogos.erro(e.getMessage());
        } catch (SQLException e) {
            Dialogos.erroBanco(e);
        }
    }
    @FXML
    void correr(ActionEvent event) {
        txt_resultado.setText((p.correr()));

    }

    @FXML
    void gritar(ActionEvent event) {
        txt_resultado.setText((p.gritar()));

    }

    @FXML
    void limpar(ActionEvent event) {
        txt_id.clear();
        modo = Modo.NENHUM;
        txt_nome.clear();
        txt_idade.clear();
        txt_altura.clear();
        txt_resultado.setText("");
    }

    @FXML
    void pedalar(ActionEvent event) {
        txt_resultado.setText((p.pedalar()));

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
                Integer id = Dialogos.pedirId("Editar pessoa", "Digite o ID da pessoa que deseja editar:");
                if (id == null) {
                    return;
                }
                Pessoa encontrada = dao.buscarPorId(id);
                if (encontrada == null) {
                    Dialogos.erro("Não existe pessoa com o ID " + id + ".");
                    return;
                }
                p = encontrada;
                idEmAcao = id;
                preencherCampos(p);
                modo = Modo.EDITANDO;
                txt_resultado.setText("Editando o registro de ID " + id + ".\nAltere os campos e clique em Editar novamente para salvar.");
                return;
            }

            // 2º clique: valida, pede confirmação e grava
            String nome = Validacao.texto(txt_nome, "Nome", 100);
            int idade = Validacao.inteiro(txt_idade, "Idade");
            double altura = Validacao.decimal(txt_altura, "Altura");

            if (!Dialogos.confirmar("Confirmar edição",
                    "Deseja realmente salvar as alterações do registro de ID " + idEmAcao + "?")) {
                return;
            }

            Pessoa atualizada = new Pessoa(idEmAcao, nome, idade, altura);
            if (!dao.atualizar(atualizada)) {
                Dialogos.erro("O registro de ID " + idEmAcao + " não existe mais.");
                return;
            }
            p = atualizada;
            modo = Modo.NENHUM;
            txt_resultado.setText("");
            Dialogos.info("Sucesso", "Pessoa editada com sucesso!");
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
                Integer id = Dialogos.pedirId("Deletar pessoa", "Digite o ID da pessoa que deseja deletar:");
                if (id == null) {
                    return;
                }
                Pessoa encontrada = dao.buscarPorId(id);
                if (encontrada == null) {
                    Dialogos.erro("Não existe pessoa com o ID " + id + ".");
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
                Dialogos.info("Sucesso", "Pessoa deletada com sucesso!");
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
            List<Pessoa> registros = dao.listarTodos();
            Dialogos.verTodos("Todas as pessoas", registros,
                new Dialogos.Coluna<Pessoa>("ID", x -> String.valueOf(x.getId())),
                new Dialogos.Coluna<Pessoa>("Nome", x -> String.valueOf(x.getNome())),
                new Dialogos.Coluna<Pessoa>("Idade", x -> String.valueOf(x.getIdade())),
                new Dialogos.Coluna<Pessoa>("Altura", x -> String.valueOf(x.getAltura())));
        } catch (SQLException e) {
            Dialogos.erroBanco(e);
        }
    }

    private void preencherCampos(Pessoa p) {
        txt_id.setText(String.valueOf(p.getId()));
        txt_nome.setText(p.getNome());
        txt_idade.setText(String.valueOf(p.getIdade()));
        txt_altura.setText(String.valueOf(p.getAltura()));
    }

    private void limparCampos() {
        txt_id.clear();
        txt_nome.clear();
        txt_idade.clear();
        txt_altura.clear();
        txt_resultado.setText("");
    }

}
