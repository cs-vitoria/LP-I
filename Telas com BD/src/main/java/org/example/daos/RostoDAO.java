package org.example.daos;

import org.example.entidades.Rosto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RostoDAO implements DAO<Rosto> {

    @Override
    public int inserir(Rosto objeto) throws SQLException {
        String sql = "INSERT INTO rosto (cor_r, contraste_r, formato_r) VALUES (?, ?, ?)";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, objeto.getCor());
            ps.setString(2, objeto.getContraste());
            ps.setString(3, objeto.getFormato());
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
    public Rosto buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_rosto, cor_r, contraste_r, formato_r FROM rosto WHERE id_rosto = ?";
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
    public boolean atualizar(Rosto objeto) throws SQLException {
        String sql = "UPDATE rosto SET cor_r = ?, contraste_r = ?, formato_r = ? WHERE id_rosto = ?";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, objeto.getCor());
            ps.setString(2, objeto.getContraste());
            ps.setString(3, objeto.getFormato());
            ps.setInt(4, objeto.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM rosto WHERE id_rosto = ?";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Rosto> listarTodos() throws SQLException {
        String sql = "SELECT id_rosto, cor_r, contraste_r, formato_r FROM rosto ORDER BY id_rosto";
        List<Rosto> lista = new ArrayList<>();
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
        return ConexaoBD.proximoId("rosto");
    }

    private Rosto mapear(ResultSet rs) throws SQLException {
        return new Rosto(
                rs.getInt("id_rosto"),
                rs.getString("cor_r"),
                rs.getString("contraste_r"),
                rs.getString("formato_r"));
    }
}
