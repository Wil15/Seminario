package controller;

import entidades.Rol;
import entidades.Persona;
import java.util.List;
import facade.RolFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "rolController")
@ViewScoped
public class RolController extends AbstractController<Rol> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isPersonaListEmpty;

    public RolController() {
        // Inform the Abstract parent controller of the concrete Rol Entity
        super(Rol.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsPersonaListEmpty();
    }

    public boolean getIsPersonaListEmpty() {
        return this.isPersonaListEmpty;
    }

    private void setIsPersonaListEmpty() {
        Rol selected = this.getSelected();
        if (selected != null) {
            RolFacade ejbFacade = (RolFacade) this.getFacade();
            this.isPersonaListEmpty = ejbFacade.isPersonaListEmpty(selected);
        } else {
            this.isPersonaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Persona entities that are
     * retrieved from Rol and returns the navigation outcome.
     *
     * @return navigation outcome for Persona page
     */
    public String navigatePersonaList() {
        Rol selected = this.getSelected();
        if (selected != null) {
            RolFacade ejbFacade = (RolFacade) this.getFacade();
            List<Persona> selectedPersonaList = ejbFacade.findPersonaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Persona_items", selectedPersonaList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/persona/index";
    }

}
