/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Persona;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entidades.Persona_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import entidades.Curso;
import entidades.Rol;
import java.util.List;

/**
 *
 * @author Wilkince
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {

    @PersistenceContext(unitName = "SeminarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }

    public boolean isCursoListEmpty(Persona entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Persona> persona = cq.from(Persona.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(persona, entity), cb.isNotEmpty(persona.get(Persona_.cursoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Curso> findCursoList(Persona entity) {
        Persona mergedEntity = this.getMergedEntity(entity);
        List<Curso> cursoList = mergedEntity.getCursoList();
        cursoList.size();
        return cursoList;
    }

    public boolean isIdRolEmpty(Persona entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Persona> persona = cq.from(Persona.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(persona, entity), cb.isNotNull(persona.get(Persona_.idRol)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Rol findIdRol(Persona entity) {
        return this.getMergedEntity(entity).getIdRol();
    }

    @Override
    public Persona findWithParents(Persona entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Persona> cq = cb.createQuery(Persona.class);
        Root<Persona> persona = cq.from(Persona.class);
        persona.fetch(Persona_.cursoList, JoinType.LEFT);
        cq.select(persona).where(cb.equal(persona, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
