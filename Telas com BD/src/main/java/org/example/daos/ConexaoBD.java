package org.example.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Único ponto de acesso ao banco de dados MySQL (banco "telas").
 * Se mudar usuário, senha, porta ou nome do banco, altere somente as constantes abaixo.
 */
public final class ConexaoBD {

    private static final String URL = "jdbc:mysql://localhost:3306/telas"
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo";
    private static final String USUARIO = "generico";
    private static final String SENHA = "senha.1234";

    private ConexaoBD() {
    }

    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do MySQL não encontrado. Verifique a dependência no pom.xml.", e);
        }
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    /**
     * Próximo valor do AUTO_INCREMENT da tabela, ou seja, o id que o próximo registro vai receber.
     */
    public static int proximoId(String tabela) throws SQLException {
        try (Connection conexao = getConexao();
             Statement st = conexao.createStatement()) {

            try {
                st.execute("SET SESSION information_schema_stats_expiry = 0");
            } catch (SQLException ignorada) {
                // MySQL 5.7 / MariaDB não têm essa variável: tudo bem
            }

            String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES "
                    + "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = '" + tabela + "'";
            try (ResultSet rs = st.executeQuery(sql)) {
                if (rs.next() && rs.getObject(1) != null) {
                    return rs.getInt(1);
                }
                return 1;
            }
        }
    }
}
