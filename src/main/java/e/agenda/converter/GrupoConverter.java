/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.agenda.converter;

import e.agenda.dao.GrupoDAO;
import e.agenda.dao.MunicipioDAO;
import e.agenda.modelo.Grupo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "grupoConverter", forClass = Grupo.class)
public class GrupoConverter implements Converter {

    GrupoDAO grupoDAO = new GrupoDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return grupoDAO.findById(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }
    

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
      
      if (value != null) {
            Grupo grupo = (Grupo) value;
            return String.valueOf(grupo.getIdGrupo());
        }
        return null;
    }

}
