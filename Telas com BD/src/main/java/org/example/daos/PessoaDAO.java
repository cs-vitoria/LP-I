package org.example.daos;

import org.example.entidades.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO implements DAO<Pessoa> {

    @Override
    public int inserir(Pessoa objeto) throws SQLException {
        String sql = "INSERT INTO pessoa (nome_p, idade_p, altura_p) VALUES (?, ?, ?)";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, objeto.getNome());
            ps.setInt(2, objeto.getIdade());
            ps.setDouble(3, objeto.getAltura());
            ps.executeUpdate();

            // pega o id que o banco acabou de gerar para este registro
            try (Statement st = conexao.createStatement();
                 ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()")) {
                rs.next();
                int id = rs.getInt(1);
                objeto.setId(id);
                return id;
            }
        }
    }

    @Override
    public Pessoa buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_pessoa, nome_p, idade_p, altura_p FROM pessoa WHERE id_pessoa = ?";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
                return null;
            }
        }
    }

    @Override
    public boolean atualizar(Pessoa objeto) throws SQLException {
        String sql = "UPDATE pessoa SET nome_p = ?, idade_p = ?, altura_p = ? WHERE id_pessoa = ?";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, objeto.getNome());
            ps.setInt(2, objeto.getIdade());
            ps.setDouble(3, objeto.getAltura());
            ps.setInt(4, objeto.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM pessoa WHERE id_pessoa = ?";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Pessoa> listarTodos() throws SQLException {
        String sql = "SELECT id_pessoa, nome_p, idade_p, altura_p FROM pessoa ORDER BY id_pessoa";
        List<Pessoa> lista = new ArrayList<>();
        try (Connection conexao = ConexaoBD.getConexao();
             Statement st = conexao.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    @Override
    public int proximoId() throws SQLException {
        return ConexaoBD.proximoId("pessoa");
    }

    private Pessoa mapear(ResultSet rs) throws SQLException {
        return new Pessoa(
                rs.getInt("id_pessoa"),
                rs.getString("nome_p"),
                rs.getInt("idade_p"),
                rs.getDouble("altura_p"));
    }
}
