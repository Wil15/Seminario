package controller;

import entidades.Bimestre;
import entidades.Nota;
import java.util.List;
import facade.BimestreFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "bimestreController")
@ViewScoped
public class BimestreController extends AbstractController<Bimestre> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isNotaListEmpty;

    public BimestreController() {
        // Inform the Abstract parent controller of the concrete Bimestre Entity
        super(Bimestre.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsNotaListEmpty();
    }

    public boolean getIsNotaListEmpty() {
        return this.isNotaListEmpty;
    }

    private void setIsNotaListEmpty() {
        Bimestre selected = this.getSelected();
        if (selected != null) {
            BimestreFacade ejbFacade = (BimestreFacade) this.getFacade();
            this.isNotaListEmpty = ejbFacade.isNotaListEmpty(selected);
        } else {
            this.isNotaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Nota entities that are
     * retrieved from Bimestre and returns the navigation outcome.
     *
     * @return navigation outcome for Nota page
     */
    public String navigateNotaList() {
        Bimestre selected = this.getSelected();
        if (selected != null) {
            BimestreFacade ejbFacade = (BimestreFacade) this.getFacade();
            List<Nota> selectedNotaList = ejbFacade.findNotaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Nota_items", selectedNotaList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/nota/index";
    }

}
