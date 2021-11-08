/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.agenda.dao;

import e.agenda.dbutil.Conexao;
import e.agenda.modelo.Grupo;
import e.agenda.modelo.Municipio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author informatica
 */
public class GrupoDAO implements GenericoDAO<Grupo>{
  private static final String SELECT_ALL ="SELECT * FROM grupo";
   private static final String SELECT_BY_ID = "SELECT * FROM grupo WHERE id_grupo=?";
    
    @Override
    public void save(Grupo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Grupo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Grupo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Grupo findById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Grupo grupo = new Grupo();
        try {
            conn = Conexao.fazerConexao();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("Nao existe nenhum registo com esse ID: " + id);

            }
            popularComDados(grupo, rs);

        } catch (SQLException ex) {
            System.out.println("Erro ao ler dados: " + ex.getMessage());
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return grupo;
    }

    @Override
    public List<Grupo> findAll() {
         Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Grupo> grupos = new ArrayList<>();
        try {
            conn = Conexao.fazerConexao();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Grupo grupo = new Grupo();
                popularComDados(grupo, rs);
                grupos.add(grupo);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler os dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection((java.sql.Connection) conn);
        }

        return grupos;
    }

    @Override
    public void popularComDados(Grupo grupo, ResultSet rs) {
        try {
            grupo.setIdGrupo(rs.getInt("id_grupo"));
              grupo.setNomeGrupo(rs.getString("nome_grupo"));
        } catch (SQLException e) {
        }
    }
    
}
