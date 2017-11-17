/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Seccion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entidades.Seccion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Estudiante;
import entidades.Grado;
import java.util.List;

/**
 *
 * @author Wilkince
 */
@Stateless
public class SeccionFacade extends AbstractFacade<Seccion> {

    @PersistenceContext(unitName = "SeminarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeccionFacade() {
        super(Seccion.class);
    }

    public boolean isEstudianteListEmpty(Seccion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Seccion> seccion = cq.from(Seccion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(seccion, entity), cb.isNotEmpty(seccion.get(Seccion_.estudianteList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Estudiante> findEstudianteList(Seccion entity) {
        Seccion mergedEntity = this.getMergedEntity(entity);
        List<Estudiante> estudianteList = mergedEntity.getEstudianteList();
        estudianteList.size();
        return estudianteList;
    }

    public boolean isIdGradoEmpty(Seccion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Seccion> seccion = cq.from(Seccion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(seccion, entity), cb.isNotNull(seccion.get(Seccion_.idGrado)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Grado findIdGrado(Seccion entity) {
        return this.getMergedEntity(entity).getIdGrado();
    }
    
}
