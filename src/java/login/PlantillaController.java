package login;

import entidades.Persona;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;

/**
 *
 * @author Victor Matías <Carné: 4490-13-5931> <vitomany@yahoo.es>
 */

@Named
@ViewScoped

public class PlantillaController implements Serializable{
    
    private static final long serialVersionUID = -3949145030165635320L;

    public void verificarSesion(){
    
        try{
        
            FacesContext contexto = FacesContext.getCurrentInstance();
            Persona usuario = (Persona) contexto.getExternalContext().getSessionMap().get("user");
            
            ServletContext servletContext = (ServletContext) contexto.getExternalContext().getContext();
            
            if ( usuario == null ){
            
                contexto.getExternalContext().redirect(servletContext.getContextPath() + "/faces/login.xhtml");
                
            }
            
        
        }catch(Exception e){
        
        }
        
    }
    
}