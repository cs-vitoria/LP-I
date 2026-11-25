package org.example.daos;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    /** Insere o objeto, grava o id gerado pelo banco nele e devolve esse id. */
    int inserir(T objeto) throws SQLException;

    /** Devolve o registro com o id informado, ou null se não existir. */
    T buscarPorId(int id) throws SQLException;

    /** Atualiza o registro que tem o mesmo id do objeto. Devolve false se o id não existir. */
    boolean atualizar(T objeto) throws SQLException;

    /** Remove o registro. Devolve false se o id não existir. */
    boolean deletar(int id) throws SQLException;

    /** Todos os registros, ordenados por id. */
    List<T> listarTodos() throws SQLException;

    /** Id que o próximo registro inserido vai receber. */
    int proximoId() throws SQLException;
}
