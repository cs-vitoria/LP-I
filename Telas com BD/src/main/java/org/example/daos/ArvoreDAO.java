package org.example.daos;

import org.example.entidades.Arvore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArvoreDAO implements DAO<Arvore> {

    @Override
    public int inserir(Arvore objeto) throws SQLException {
        String sql = "INSERT INTO arvore (folhagem_a, especie_a, idade_a) VALUES (?, ?, ?)";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, objeto.getFolhagem());
            ps.setString(2, objeto.getEspecie());
            ps.setInt(3, objeto.getIdade());
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
    public Arvore buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_arvore, folhagem_a, especie_a, idade_a FROM arvore WHERE id_arvore = ?";
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
    public boolean atualizar(Arvore objeto) throws SQLException {
        String sql = "UPDATE arvore SET folhagem_a = ?, especie_a = ?, idade_a = ? WHERE id_arvore = ?";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, objeto.getFolhagem());
            ps.setString(2, objeto.getEspecie());
            ps.setInt(3, objeto.getIdade());
            ps.setInt(4, objeto.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM arvore WHERE id_arvore = ?";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Arvore> listarTodos() throws SQLException {
        String sql = "SELECT id_arvore, folhagem_a, especie_a, idade_a FROM arvore ORDER BY id_arvore";
        List<Arvore> lista = new ArrayList<>();
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
        return ConexaoBD.proximoId("arvore");
    }

    private Arvore mapear(ResultSet rs) throws SQLException {
        return new Arvore(
                rs.getInt("id_arvore"),
                rs.getString("folhagem_a"),
                rs.getString("especie_a"),
                rs.getInt("idade_a"));
    }
}
