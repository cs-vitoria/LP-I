package org.example.daos;

import org.example.entidades.Cabelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CabeloDAO implements DAO<Cabelo> {

    @Override
    public int inserir(Cabelo objeto) throws SQLException {
        String sql = "INSERT INTO cabelo (cor_c, tamanho_c, textura_c) VALUES (?, ?, ?)";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, objeto.getCor());
            ps.setDouble(2, objeto.getTamanho());
            ps.setString(3, objeto.getTextura());
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
    public Cabelo buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_cabelo, cor_c, tamanho_c, textura_c FROM cabelo WHERE id_cabelo = ?";
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
    public boolean atualizar(Cabelo objeto) throws SQLException {
        String sql = "UPDATE cabelo SET cor_c = ?, tamanho_c = ?, textura_c = ? WHERE id_cabelo = ?";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, objeto.getCor());
            ps.setDouble(2, objeto.getTamanho());
            ps.setString(3, objeto.getTextura());
            ps.setInt(4, objeto.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM cabelo WHERE id_cabelo = ?";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Cabelo> listarTodos() throws SQLException {
        String sql = "SELECT id_cabelo, cor_c, tamanho_c, textura_c FROM cabelo ORDER BY id_cabelo";
        List<Cabelo> lista = new ArrayList<>();
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
        return ConexaoBD.proximoId("cabelo");
    }

    private Cabelo mapear(ResultSet rs) throws SQLException {
        return new Cabelo(
                rs.getInt("id_cabelo"),
                rs.getString("cor_c"),
                Double.parseDouble(String.valueOf(rs.getFloat("tamanho_c"))),
                rs.getString("textura_c"));
    }
}
