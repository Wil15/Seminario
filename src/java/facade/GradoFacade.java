/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Grado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entidades.Grado_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Seccion;
import entidades.Curso;
import java.util.List;

/**
 *
 * @author Wilkince
 */
@Stateless
public class GradoFacade extends AbstractFacade<Grado> {

    @PersistenceContext(unitName = "SeminarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GradoFacade() {
        super(Grado.class);
    }

    public boolean isSeccionListEmpty(Grado entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Grado> grado = cq.from(Grado.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(grado, entity), cb.isNotEmpty(grado.get(Grado_.seccionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Seccion> findSeccionList(Grado entity) {
        Grado mergedEntity = this.getMergedEntity(entity);
        List<Seccion> seccionList = mergedEntity.getSeccionList();
        seccionList.size();
        return seccionList;
    }

    public boolean isCursoListEmpty(Grado entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Grado> grado = cq.from(Grado.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(grado, entity), cb.isNotEmpty(grado.get(Grado_.cursoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Curso> findCursoList(Grado entity) {
        Grado mergedEntity = this.getMergedEntity(entity);
        List<Curso> cursoList = mergedEntity.getCursoList();
        cursoList.size();
        return cursoList;
    }
    
}
