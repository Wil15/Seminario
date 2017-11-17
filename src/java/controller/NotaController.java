package controller;

import entidades.Nota;
import facade.NotaFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "notaController")
@ViewScoped
public class NotaController extends AbstractController<Nota> {

    @Inject
    private BimestreController idBimestreController;
    @Inject
    private CursoController idCursoController;
    @Inject
    private EstudianteController idEstudianteController;
    @Inject
    private MobilePageController mobilePageController;

    public NotaController() {
        // Inform the Abstract parent controller of the concrete Nota Entity
        super(Nota.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idBimestreController.setSelected(null);
        idCursoController.setSelected(null);
        idEstudianteController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Bimestre controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdBimestre(ActionEvent event) {
        Nota selected = this.getSelected();
        if (selected != null && idBimestreController.getSelected() == null) {
            idBimestreController.setSelected(selected.getIdBimestre());
        }
    }

    /**
     * Sets the "selected" attribute of the Curso controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdCurso(ActionEvent event) {
        Nota selected = this.getSelected();
        if (selected != null && idCursoController.getSelected() == null) {
            idCursoController.setSelected(selected.getIdCurso());
        }
    }

    /**
     * Sets the "selected" attribute of the Estudiante controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdEstudiante(ActionEvent event) {
        Nota selected = this.getSelected();
        if (selected != null && idEstudianteController.getSelected() == null) {
            idEstudianteController.setSelected(selected.getIdEstudiante());
        }
    }

}
