/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.agenda.dao;

import e.agenda.dbutil.Conexao;
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
public class MunicipioDAO implements GenericoDAO<Municipio>{
  private static final String SELECT_ALL ="SELECT * FROM municipio";
   private static final String SELECT_BY_ID = "SELECT * FROM municipio WHERE id_municipio=?";
    
    @Override
    public void save(Municipio t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Municipio t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Municipio t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Municipio findById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Municipio municipio = new Municipio();
        try {
            conn = Conexao.fazerConexao();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("Nao existe nenhum registo com esse ID: " + id);

            }
            popularComDados(municipio, rs);

        } catch (SQLException ex) {
            System.out.println("Erro ao ler dados: " + ex.getMessage());
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return municipio;
    }

    @Override
    public List<Municipio> findAll() {
         Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Municipio> municipios = new ArrayList<>();
        try {
            conn = Conexao.fazerConexao();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Municipio municipio = new Municipio();
                popularComDados(municipio, rs);
                municipios.add(municipio);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler os dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection((java.sql.Connection) conn);
        }

        return municipios;
    }

    @Override
    public void popularComDados(Municipio municipio, ResultSet rs) {
        try {
            municipio.setIdMunicipio(rs.getInt("id_municipio"));
              municipio.setNomeMunicipio(rs.getString("nome_municipio"));
        } catch (SQLException e) {
        }
    }
    
}
