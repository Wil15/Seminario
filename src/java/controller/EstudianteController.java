package controller;

import entidades.Estudiante;
import entidades.Nota;
import java.util.List;
import facade.EstudianteFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "estudianteController")
@ViewScoped
public class EstudianteController extends AbstractController<Estudiante> {

    @Inject
    private SeccionController idSeccionController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isNotaListEmpty;

    public EstudianteController() {
        // Inform the Abstract parent controller of the concrete Estudiante Entity
        super(Estudiante.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idSeccionController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsNotaListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Seccion controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdSeccion(ActionEvent event) {
        Estudiante selected = this.getSelected();
        if (selected != null && idSeccionController.getSelected() == null) {
            idSeccionController.setSelected(selected.getIdSeccion());
        }
    }

    public boolean getIsNotaListEmpty() {
        return this.isNotaListEmpty;
    }

    private void setIsNotaListEmpty() {
        Estudiante selected = this.getSelected();
        if (selected != null) {
            EstudianteFacade ejbFacade = (EstudianteFacade) this.getFacade();
            this.isNotaListEmpty = ejbFacade.isNotaListEmpty(selected);
        } else {
            this.isNotaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Nota entities that are
     * retrieved from Estudiante and returns the navigation outcome.
     *
     * @return navigation outcome for Nota page
     */
    public String navigateNotaList() {
        Estudiante selected = this.getSelected();
        if (selected != null) {
            EstudianteFacade ejbFacade = (EstudianteFacade) this.getFacade();
            List<Nota> selectedNotaList = ejbFacade.findNotaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Nota_items", selectedNotaList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/nota/index";
    }

}
