package e.agenda.dao;

import e.agenda.dbutil.Conexao;
import e.agenda.modelo.Contacto;
import e.agenda.modelo.Municipio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactoDAO implements GenericoDAO<Contacto> {

    private static final String INSERIR = "INSERT INTO contacto(nome,sobrenome,casa,rua,bairro,distrito,data_nascimento, telefome_movicel, telefone_unitel,telefone_fixo,e_mail,url_foto,foto,id_municipio,id_grupo) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE contacto SET nome = ? , sobrenome =?, casa= ?, bairro = ?, distrito = ?, data_nascimento=?, url_foto = ?, foto = ?, id_municipio =?  WHERE id = ?";
    private static final String DELETE = "DELETE FROM contacto WHERE id=?;";
    private static final String SELECT_ALL = "SELECT * FROM contacto c INNER JOIN municipio m ON m.id_municipio = c.id_municipio";
    private static final String SELECT_BY_ID = "SELECT c.id,c.nome,c.sobrenome,c.casa,c.bairro,c.distrito,c.data_nascimento,c.url_foto, c.foto, m.nome_municipio FROM contacto c INNER JOIN municipio m ON m.id_municipio = c.id_municipio WHERE id = ?";

    @Override
    public void save(Contacto contacto) {
        Connection conn = null;
        PreparedStatement ps = null;
        if (contacto == null) {
            System.err.println("O campo anterior não pode ser nulo!");
        }

        try {
            conn = Conexao.fazerConexao();
            ps = conn.prepareStatement(INSERIR);
            ps.setString(1, contacto.getNome());
            ps.setString(2, contacto.getSobrenome());
            ps.setString(3, contacto.getCasa());
            ps.setString(4, contacto.getRua());
            ps.setString(5, contacto.getBairro());
            ps.setString(6, contacto.getDistrito());
            ps.setDate(7, new java.sql.Date(contacto.getDataNascimento().getTime()));
            ps.setString(8, contacto.getTelefoneMovicel());
            ps.setString(9, contacto.getTelefoneUnitel());
            ps.setString(10, contacto.getTelefoneFixo());
            ps.setString(11, contacto.getEmail());
            ps.setString(12, contacto.getUrlFoto());
            ps.setBytes(13, contacto.getFoto());
            ps.setInt(14, contacto.getMunicipio().getIdMunicipio());
            ps.setInt(15, contacto.getGrupo().getIdGrupo());
            
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados inseridos com Sucesso: " + ps.getUpdateCount());

            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados:ContactoDAO:Save " + e.getMessage());

        } finally {
            Conexao.closeConnection(conn, ps);
        }

    }

    @Override
    public void update(Contacto contacto) {
        Connection conn = null;
        PreparedStatement ps = null;

        if (contacto == null) {
            System.err.println("O campo anterior não pode ser nulo!");
        }

        try {
            conn = Conexao.fazerConexao();
            ps = conn.prepareStatement(INSERIR);
            ps.setString(1, contacto.getNome());
            ps.setString(2, contacto.getSobrenome());
            ps.setString(3, contacto.getCasa());
            ps.setString(4, contacto.getRua());
            ps.setString(5, contacto.getBairro());
            ps.setString(6, contacto.getDistrito());
            ps.setDate(7, new java.sql.Date(contacto.getDataNascimento().getTime()));
            ps.setString(8, contacto.getUrlFoto());
            ps.setBytes(9, contacto.getFoto());
            ps.setInt(10, contacto.getMunicipio().getIdMunicipio());
            ps.setInt(11, contacto.getId());
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados actualizados com Sucesso:ContactoDAO:update " + ps.getUpdateCount());

            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());

        } finally {
            Conexao.closeConnection(conn, ps);
        }

    }

    @Override
    public void delete(Contacto contacto) {
        Connection conn = null;
        PreparedStatement ps = null;

        if (contacto == null) {
            System.err.println("O valor passado não pode ser nulo");
        }
        try {
            conn = Conexao.fazerConexao();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, contacto.getId());
            int retorno = ps.executeUpdate();

            if (retorno > 0) {
                System.out.println("Dados eliminados com sucesso: " + ps.getUpdateCount());

            }

        } catch (SQLException ex) {
            System.err.println("Erro ao eliminar dados: " + ex.getLocalizedMessage());

        } finally {
            Conexao.closeConnection(conn, ps);
            {
            }
        }
    }

    @Override
    public Contacto findById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Contacto contacto = new Contacto();
        try {
            conn = Conexao.fazerConexao();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("Nao existe nenhum registo com esse ID: " + id);

            }
            popularComDados(contacto, rs);

        } catch (SQLException ex) {
            System.out.println("Erro ao ler dados: " + ex.getMessage());
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return contacto;
    }

    @Override
    public List<Contacto> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Contacto> contactos = new ArrayList<>();
        try {
            conn = Conexao.fazerConexao();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Contacto contacto = new Contacto();
                popularComDados(contacto, rs);
                contactos.add(contacto);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler os dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection((java.sql.Connection) conn);
        }

        return contactos;
    }

    @Override
    public void popularComDados(Contacto contacto, ResultSet rs) {
        try {
            contacto.setId(rs.getInt("c.id"));
            contacto.setNome(rs.getString("c.nome"));
            contacto.setSobrenome(rs.getString("c.sobrenome"));
            contacto.setCasa(rs.getString("c.casa"));
            contacto.setRua(rs.getString("rua"));
            contacto.setBairro(rs.getString("bairro"));
            contacto.setDistrito(rs.getString("distrito"));
            contacto.setDataNascimento(rs.getDate("data_nascimento"));
            contacto.setUrlFoto(rs.getString("c.url_foto"));
            contacto.setFoto(rs.getBytes("foto"));
            
            Municipio municipio = new Municipio();
            municipio.setNomeMunicipio(rs.getString("nome_municipio"));
            contacto.setMunicipio(municipio);
            
        } catch (SQLException e) {
        }
    }

}
