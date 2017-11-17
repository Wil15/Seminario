/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import entidades.Persona;
import facade.PersonaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Wilkince
 */
@Named
@SessionScoped
public class LoginControler implements Serializable {

    private static final long serialVersionUID = 1979050895618488871L;
    private String usuario;
    private String password;
    private Persona persona;
    @Inject
    private PersonaFacade personaFacade;

    @PersistenceContext(unitName = "SeminarioPU")
    private EntityManager em;    
    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String inicioSesion() {
        FacesContext context = FacesContext.getCurrentInstance();
        Query query = em.createNamedQuery("Persona.findByUsuario");
        query.setParameter("usuario", usuario);
        query.setMaxResults(1);
        
        List<Persona> listaPersona = query.getResultList();
        if(listaPersona.size() > 0){
            Persona personaNueva = (Persona) listaPersona.get(0);
            if(personaNueva.getPassword().equals(this.password))
                context.getExternalContext().getSessionMap().put("user", personaNueva);
                return "/faces/index.xhtml";
        }

        return "/faces/login.xhtml";

    }
    

    public String isUserLogued(){
        if ( FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .get("user") == null
        ){
            return "false";
        }
        else
            return "true";
    }    
    
}
