package controller;

import entidades.Seccion;
import entidades.Estudiante;
import java.util.List;
import facade.SeccionFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "seccionController")
@ViewScoped
public class SeccionController extends AbstractController<Seccion> {

    @Inject
    private GradoController idGradoController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isEstudianteListEmpty;

    public SeccionController() {
        // Inform the Abstract parent controller of the concrete Seccion Entity
        super(Seccion.class);
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
        this.setIsEstudianteListEmpty();
    }

    public boolean getIsEstudianteListEmpty() {
        return this.isEstudianteListEmpty;
    }

    private void setIsEstudianteListEmpty() {
        Seccion selected = this.getSelected();
        if (selected != null) {
            SeccionFacade ejbFacade = (SeccionFacade) this.getFacade();
            this.isEstudianteListEmpty = ejbFacade.isEstudianteListEmpty(selected);
        } else {
            this.isEstudianteListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Estudiante entities that
     * are retrieved from Seccion and returns the navigation outcome.
     *
     * @return navigation outcome for Estudiante page
     */
    public String navigateEstudianteList() {
        Seccion selected = this.getSelected();
        if (selected != null) {
            SeccionFacade ejbFacade = (SeccionFacade) this.getFacade();
            List<Estudiante> selectedEstudianteList = ejbFacade.findEstudianteList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Estudiante_items", selectedEstudianteList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/estudiante/index";
    }

    /**
     * Sets the "selected" attribute of the Grado controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdGrado(ActionEvent event) {
        Seccion selected = this.getSelected();
        if (selected != null && idGradoController.getSelected() == null) {
            idGradoController.setSelected(selected.getIdGrado());
        }
    }

}
