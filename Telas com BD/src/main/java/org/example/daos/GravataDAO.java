package org.example.daos;

import org.example.entidades.Gravata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GravataDAO implements DAO<Gravata> {

    @Override
    public int inserir(Gravata objeto) throws SQLException {
        String sql = "INSERT INTO gravata (textura_g, estampa_g, tamanho_g) VALUES (?, ?, ?)";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, objeto.getTextura());
            ps.setString(2, objeto.getEstampa());
            ps.setString(3, objeto.getTamanho());
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
    public Gravata buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_gravata, textura_g, estampa_g, tamanho_g FROM gravata WHERE id_gravata = ?";
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
    public boolean atualizar(Gravata objeto) throws SQLException {
        String sql = "UPDATE gravata SET textura_g = ?, estampa_g = ?, tamanho_g = ? WHERE id_gravata = ?";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, objeto.getTextura());
            ps.setString(2, objeto.getEstampa());
            ps.setString(3, objeto.getTamanho());
            ps.setInt(4, objeto.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM gravata WHERE id_gravata = ?";
        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Gravata> listarTodos() throws SQLException {
        String sql = "SELECT id_gravata, textura_g, estampa_g, tamanho_g FROM gravata ORDER BY id_gravata";
        List<Gravata> lista = new ArrayList<>();
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
        return ConexaoBD.proximoId("gravata");
    }

    private Gravata mapear(ResultSet rs) throws SQLException {
        return new Gravata(
                rs.getInt("id_gravata"),
                rs.getString("textura_g"),
                rs.getString("estampa_g"),
                rs.getString("tamanho_g"));
    }
}
