/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.notas;

import entidades.Curso;

import entidades.Persona;
import entidades.Seccion;
import facade.CursoFacade;
import facade.GradoFacade;
import facade.PersonaFacade;
import facade.SeccionFacade;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named
@SessionScoped
/**
 *
 * @author Wilkince
 */
public class ControladorInicioNotas implements Serializable {

    private Persona persona = null;
    private static final long serialVersionUID = 1979050895618488871L;
    private List<Curso> listaCurso; 
    private List<Seccion> listaSeccion;
    
    private Seccion seccion = null;
    private Integer idCurso = null;
    private Integer idSeccion = null;
    //******
    //*******      
    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }    
    
    public Integer getIdSeccion() {
        return idSeccion;
    }

    //*****
    public void setIdSeccion(Integer idSeccion) {
        this.idSeccion = idSeccion;
    }
        
    //*****
    
    //private String cursos;
    @Inject
    private PersonaFacade personaFacade;
    //*******
    @Inject
    private CursoFacade cursoFacade;
    //*******
    @Inject
    private SeccionFacade seccionFacade;
    
    @PostConstruct
    public void init(){
        this.persona = (Persona) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .get("user");
        
        listaCurso = personaFacade.findCursoList(persona); 
        
    }
    
    //******
    public List<Seccion> getListaSeccion() {
        return listaSeccion;
    }
    //******
    public void setListaSeccion(List<Seccion> listaSeccion) {
        this.listaSeccion = listaSeccion;
    }
    
    
    public List<Curso> getListaCurso() {
        return listaCurso;
    }

    public void setListaCurso(List<Curso> listaCurso) {
        this.listaCurso = listaCurso;
    }

    public List notas(String nombreCurso) {
  
        return listaCurso;
    }

}
