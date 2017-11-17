package controller;

import entidades.Curso;
import entidades.Persona;
import entidades.Nota;
import java.util.List;
import facade.CursoFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "cursoController")
@ViewScoped
public class CursoController extends AbstractController<Curso> {

    @Inject
    private GradoController idGradoController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isPersonaListEmpty;
    private boolean isNotaListEmpty;

    public CursoController() {
        // Inform the Abstract parent controller of the concrete Curso Entity
        super(Curso.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idGradoController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsPersonaListEmpty();
        this.setIsNotaListEmpty();
    }

    public boolean getIsPersonaListEmpty() {
        return this.isPersonaListEmpty;
    }

    private void setIsPersonaListEmpty() {
        Curso selected = this.getSelected();
        if (selected != null) {
            CursoFacade ejbFacade = (CursoFacade) this.getFacade();
            this.isPersonaListEmpty = ejbFacade.isPersonaListEmpty(selected);
        } else {
            this.isPersonaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Persona entities that are
     * retrieved from Curso and returns the navigation outcome.
     *
     * @return navigation outcome for Persona page
     */
    public String navigatePersonaList() {
        Curso selected = this.getSelected();
        if (selected != null) {
            CursoFacade ejbFacade = (CursoFacade) this.getFacade();
            List<Persona> selectedPersonaList = ejbFacade.findPersonaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Persona_items", selectedPersonaList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/persona/index";
    }

    /**
     * Sets the "selected" attribute of the Grado controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdGrado(ActionEvent event) {
        Curso selected = this.getSelected();
        if (selected != null && idGradoController.getSelected() == null) {
            idGradoController.setSelected(selected.getIdGrado());
        }
    }

    public boolean getIsNotaListEmpty() {
        return this.isNotaListEmpty;
    }

    private void setIsNotaListEmpty() {
        Curso selected = this.getSelected();
        if (selected != null) {
            CursoFacade ejbFacade = (CursoFacade) this.getFacade();
            this.isNotaListEmpty = ejbFacade.isNotaListEmpty(selected);
        } else {
            this.isNotaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Nota entities that are
     * retrieved from Curso and returns the navigation outcome.
     *
     * @return navigation outcome for Nota page
     */
    public String navigateNotaList() {
        Curso selected = this.getSelected();
        if (selected != null) {
            CursoFacade ejbFacade = (CursoFacade) this.getFacade();
            List<Nota> selectedNotaList = ejbFacade.findNotaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Nota_items", selectedNotaList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/nota/index";
    }

}
