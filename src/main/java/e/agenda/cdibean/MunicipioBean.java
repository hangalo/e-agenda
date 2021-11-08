/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.agenda.cdibean;

import e.agenda.dao.MunicipioDAO;
import e.agenda.modelo.Municipio;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "municipioBean")
@RequestScoped
public class MunicipioBean {

    List<Municipio> municipios;
    MunicipioDAO municipioDAO;

    @PostConstruct
    public void inicializar() {
        municipios = new ArrayList<>();
        municipioDAO = new MunicipioDAO();
        municipios = municipioDAO.findAll();
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    
    
}
