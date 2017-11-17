/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Nota;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entidades.Nota_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Bimestre;
import entidades.Curso;
import entidades.Estudiante;

/**
 *
 * @author Wilkince
 */
@Stateless
public class NotaFacade extends AbstractFacade<Nota> {

    @PersistenceContext(unitName = "SeminarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotaFacade() {
        super(Nota.class);
    }

    public boolean isIdBimestreEmpty(Nota entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Nota> nota = cq.from(Nota.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(nota, entity), cb.isNotNull(nota.get(Nota_.idBimestre)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Bimestre findIdBimestre(Nota entity) {
        return this.getMergedEntity(entity).getIdBimestre();
    }

    public boolean isIdCursoEmpty(Nota entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Nota> nota = cq.from(Nota.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(nota, entity), cb.isNotNull(nota.get(Nota_.idCurso)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Curso findIdCurso(Nota entity) {
        return this.getMergedEntity(entity).getIdCurso();
    }

    public boolean isIdEstudianteEmpty(Nota entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Nota> nota = cq.from(Nota.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(nota, entity), cb.isNotNull(nota.get(Nota_.idEstudiante)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Estudiante findIdEstudiante(Nota entity) {
        return this.getMergedEntity(entity).getIdEstudiante();
    }
    
}
