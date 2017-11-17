package controller;

import entidades.Grado;
import entidades.Seccion;
import entidades.Curso;
import java.util.List;
import facade.GradoFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "gradoController")
@ViewScoped
public class GradoController extends AbstractController<Grado> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isSeccionListEmpty;
    private boolean isCursoListEmpty;

    public GradoController() {
        // Inform the Abstract parent controller of the concrete Grado Entity
        super(Grado.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsSeccionListEmpty();
        this.setIsCursoListEmpty();
    }

    public boolean getIsSeccionListEmpty() {
        return this.isSeccionListEmpty;
    }

    private void setIsSeccionListEmpty() {
        Grado selected = this.getSelected();
        if (selected != null) {
            GradoFacade ejbFacade = (GradoFacade) this.getFacade();
            this.isSeccionListEmpty = ejbFacade.isSeccionListEmpty(selected);
        } else {
            this.isSeccionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Seccion entities that are
     * retrieved from Grado and returns the navigation outcome.
     *
     * @return navigation outcome for Seccion page
     */
    public String navigateSeccionList() {
        Grado selected = this.getSelected();
        if (selected != null) {
            GradoFacade ejbFacade = (GradoFacade) this.getFacade();
            List<Seccion> selectedSeccionList = ejbFacade.findSeccionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Seccion_items", selectedSeccionList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/seccion/index";
    }

    public boolean getIsCursoListEmpty() {
        return this.isCursoListEmpty;
    }

    private void setIsCursoListEmpty() {
        Grado selected = this.getSelected();
        if (selected != null) {
            GradoFacade ejbFacade = (GradoFacade) this.getFacade();
            this.isCursoListEmpty = ejbFacade.isCursoListEmpty(selected);
        } else {
            this.isCursoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Curso entities that are
     * retrieved from Grado and returns the navigation outcome.
     *
     * @return navigation outcome for Curso page
     */
    public String navigateCursoList() {
        Grado selected = this.getSelected();
        if (selected != null) {
            GradoFacade ejbFacade = (GradoFacade) this.getFacade();
            List<Curso> selectedCursoList = ejbFacade.findCursoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Curso_items", selectedCursoList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/curso/index";
    }

}
