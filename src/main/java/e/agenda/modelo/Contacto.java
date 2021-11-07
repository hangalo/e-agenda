package e.agenda.modelo;

import java.util.Date;
import java.util.Objects;


public class Contacto {
    private Integer id;
    private String nome;
    private String sobrenome;
    private String casa;
    private String rua;
    private String bairro;
    private String distrito;
    private Date dataNascimento;
    private String urlFoto;
    private byte[] foto;

    public Contacto() {
    }

    public Contacto(Integer id, String nome, String sobrenome, String casa, String rua, String bairro, String distrito, Date dataNascimento, String urlFoto, byte[] foto) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.casa = casa;
        this.rua = rua;
        this.bairro = bairro;
        this.distrito = distrito;
        this.dataNascimento = dataNascimento;
        this.urlFoto = urlFoto;
        this.foto = foto;
    }

    
   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public byte[] getFoto() {
        return foto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contacto other = (Contacto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Contacto{" + "nome=" + nome + ", sobrenome=" + sobrenome + '}';
    }

 
    
    
    
}
