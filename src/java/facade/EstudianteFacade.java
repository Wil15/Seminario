/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Estudiante;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entidades.Estudiante_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Seccion;
import entidades.Nota;
import java.util.List;

/**
 *
 * @author Wilkince
 */
@Stateless
public class EstudianteFacade extends AbstractFacade<Estudiante> {

    @PersistenceContext(unitName = "SeminarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {
        super(Estudiante.class);
    }

    public boolean isIdSeccionEmpty(Estudiante entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Estudiante> estudiante = cq.from(Estudiante.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(estudiante, entity), cb.isNotNull(estudiante.get(Estudiante_.idSeccion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Seccion findIdSeccion(Estudiante entity) {
        return this.getMergedEntity(entity).getIdSeccion();
    }

    public boolean isNotaListEmpty(Estudiante entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Estudiante> estudiante = cq.from(Estudiante.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(estudiante, entity), cb.isNotEmpty(estudiante.get(Estudiante_.notaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Nota> findNotaList(Estudiante entity) {
        Estudiante mergedEntity = this.getMergedEntity(entity);
        List<Nota> notaList = mergedEntity.getNotaList();
        notaList.size();
        return notaList;
    }
    
}
