/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.agenda.cdibean;

import e.agenda.dao.GrupoDAO;
import e.agenda.dao.MunicipioDAO;
import e.agenda.modelo.Grupo;
import e.agenda.modelo.Municipio;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "grupoBean")
@RequestScoped
public class GrupoBean {

    List<Grupo> grupos;
    GrupoDAO grupoDAO;

    @PostConstruct
    public void inicializar() {
        grupos = new ArrayList<>();
        grupoDAO = new GrupoDAO();
        grupos = grupoDAO.findAll();
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

   

    
    
}
