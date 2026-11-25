package org.example.daos;

import org.example.entidades.Bicicleta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BicicletaDAO implements DAO<Bicicleta> {

    @Override
    public int inserir(Bicicleta objeto) throws SQLException {
        String sql = "INSERT INTO bicicleta (cor_b, modelo_b, marca_b) VALUES (?, ?, ?)";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, objeto.getCor());
            ps.setString(2, objeto.getModelo());
            ps.setString(3, objeto.getMarca());
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
    public Bicicleta buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_bicicleta, cor_b, modelo_b, marca_b FROM bicicleta WHERE id_bicicleta = ?";
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
    public boolean atualizar(Bicicleta objeto) throws SQLException {
        String sql = "UPDATE bicicleta SET cor_b = ?, modelo_b = ?, marca_b = ? WHERE id_bicicleta = ?";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, objeto.getCor());
            ps.setString(2, objeto.getModelo());
            ps.setString(3, objeto.getMarca());
            ps.setInt(4, objeto.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM bicicleta WHERE id_bicicleta = ?";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Bicicleta> listarTodos() throws SQLException {
        String sql = "SELECT id_bicicleta, cor_b, modelo_b, marca_b FROM bicicleta ORDER BY id_bicicleta";
        List<Bicicleta> lista = new ArrayList<>();
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
        return ConexaoBD.proximoId("bicicleta");
    }

    private Bicicleta mapear(ResultSet rs) throws SQLException {
        return new Bicicleta(
                rs.getInt("id_bicicleta"),
                rs.getString("cor_b"),
                rs.getString("modelo_b"),
                rs.getString("marca_b"));
    }
}
