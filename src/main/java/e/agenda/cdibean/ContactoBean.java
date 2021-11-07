/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.agenda.cdibean;

import e.agenda.dao.ContactoDAO;
import e.agenda.fileUtil.FicheiroUtil;
import e.agenda.modelo.Contacto;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author informatica
 */
@Named(value = "contactoBean")
@SessionScoped
public class ContactoBean implements Serializable {

    private Part foto;
    Contacto contacto;
    ContactoDAO contactoDAO;
    List<Contacto> contactos;
    private boolean uploaded;

    @PostConstruct
    public void inicializar() {
        contacto = new Contacto();
        contactos = new ArrayList<>();
        contactoDAO = new ContactoDAO();

    }

    public void salvar() {
        contactoDAO.save(contacto);
        contacto = new Contacto();
        FacesMessage message = new FacesMessage("Dados guardados com sucesso");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Contacto> listaContactos() {
        return contactos = contactoDAO.findAll();
    }

    public void doUpload() {

        try {
            InputStream in = foto.getInputStream();
            File f = new File(FicheiroUtil.getPathPastaAplicacaoJSF() + foto.getSubmittedFileName());

            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            byte[] buffer = new byte[1024 * 1024 * 100];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.close();
            in.close();
            //Conteudo para a o campo foto da tabela aluno. Carrega uma string com o nome e extensao do ficheiro
            contacto.setUrlFoto(foto.getSubmittedFileName());
            //Conteudo em byte do ficheiro. Guarda o conteudo em bytes num campo da tabela.
            byte[] fotoByte = IOUtils.toByteArray(foto.getInputStream());

            contacto.setFoto(fotoByte);

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("path", f.getAbsolutePath());
            uploaded = true;
            FacesMessage msg = new FacesMessage("Ficheito", "\t\t" + f.getName() + "\t" + "\t" + "carregado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

}
