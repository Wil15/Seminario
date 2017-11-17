package controller;

import entidades.Persona;
import entidades.Curso;
import java.util.List;
import facade.PersonaFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "personaController")
@ViewScoped
public class PersonaController extends AbstractController<Persona> {

    @Inject
    private RolController idRolController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isCursoListEmpty;

    public PersonaController() {
        // Inform the Abstract parent controller of the concrete Persona Entity
        super(Persona.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idRolController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCursoListEmpty();
    }

    public boolean getIsCursoListEmpty() {
        return this.isCursoListEmpty;
    }

    private void setIsCursoListEmpty() {
        Persona selected = this.getSelected();
        if (selected != null) {
            PersonaFacade ejbFacade = (PersonaFacade) this.getFacade();
            this.isCursoListEmpty = (selected.getCursoList() == null || selected.getCursoList().isEmpty());
        } else {
            this.isCursoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Curso entities that are
     * retrieved from Persona and returns the navigation outcome.
     *
     * @return navigation outcome for Curso page
     */
    public String navigateCursoList() {
        Persona selected = this.getSelected();
        if (selected != null) {
            PersonaFacade ejbFacade = (PersonaFacade) this.getFacade();
            // Note: CursoList has already been read as is initialized
            List<Curso> selectedCursoList = selected.getCursoList();
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Curso_items", selectedCursoList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/curso/index";
    }

    /**
     * Sets the "selected" attribute of the Rol controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdRol(ActionEvent event) {
        Persona selected = this.getSelected();
        if (selected != null && idRolController.getSelected() == null) {
            idRolController.setSelected(selected.getIdRol());
        }
    }

}
