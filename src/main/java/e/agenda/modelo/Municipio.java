/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.agenda.modelo;

import java.util.Objects;


public class Municipio {
    private Integer idMunicipio;
    private String nomeMunicipio;

    public Municipio() {
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.idMunicipio);
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
        final Municipio other = (Municipio) obj;
        if (!Objects.equals(this.idMunicipio, other.idMunicipio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Municipio{" + "nomeMunicipio=" + nomeMunicipio + '}';
    }
    
    
}
