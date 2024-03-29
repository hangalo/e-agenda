/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.agenda.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author informatica
 */
public class Conexao {

    public static Connection fazerConexao() {
        String URLMYSQL8 = "jdbc:mysql://localhost:3306/agenda? useSSL=false& serverTimezone=UTC";
        String URLMYSQL5_6 = "jdbc:mysql://localhost:3306/agenda";
        String DRIVERMYSQL8 = "com.mysql.cj.jdbc.Driver";
        String DRIVERMYSQL5_6 = "com.mysql.jdbc.Driver";
        String USER = "root";
        String PASSWORD = "root";
        Connection conn;
        try {
            Class.forName(DRIVERMYSQL5_6);
            conn = DriverManager.getConnection(URLMYSQL5_6, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Erro na conexao com a base de dados: " + ex.getMessage());
            return null;
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        close(conn, null, null);
    }

    public static void closeConnection(Connection conn, PreparedStatement ps) {
        close(conn, ps, null);
    }

    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {
        close(conn, ps, rs);
    }

    private static void close(Connection conn, PreparedStatement ps, ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao desalocar recurso: " + ex.getMessage());
        }

    }

}
