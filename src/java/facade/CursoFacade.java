/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Curso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entidades.Curso_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Persona;
import entidades.Grado;
import entidades.Nota;
import java.util.List;

/**
 *
 * @author Wilkince
 */
@Stateless
public class CursoFacade extends AbstractFacade<Curso> {

    @PersistenceContext(unitName = "SeminarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoFacade() {
        super(Curso.class);
    }

    public boolean isPersonaListEmpty(Curso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Curso> curso = cq.from(Curso.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(curso, entity), cb.isNotEmpty(curso.get(Curso_.personaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Persona> findPersonaList(Curso entity) {
        Curso mergedEntity = this.getMergedEntity(entity);
        List<Persona> personaList = mergedEntity.getPersonaList();
        personaList.size();
        return personaList;
    }

    public boolean isIdGradoEmpty(Curso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Curso> curso = cq.from(Curso.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(curso, entity), cb.isNotNull(curso.get(Curso_.idGrado)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Grado findIdGrado(Curso entity) {
        return this.getMergedEntity(entity).getIdGrado();
    }

    public boolean isNotaListEmpty(Curso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Curso> curso = cq.from(Curso.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(curso, entity), cb.isNotEmpty(curso.get(Curso_.notaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Nota> findNotaList(Curso entity) {
        Curso mergedEntity = this.getMergedEntity(entity);
        List<Nota> notaList = mergedEntity.getNotaList();
        notaList.size();
        return notaList;
    }
    
}
