/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Bimestre;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entidades.Bimestre_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Nota;
import java.util.List;

/**
 *
 * @author Wilkince
 */
@Stateless
public class BimestreFacade extends AbstractFacade<Bimestre> {

    @PersistenceContext(unitName = "SeminarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BimestreFacade() {
        super(Bimestre.class);
    }

    public boolean isNotaListEmpty(Bimestre entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bimestre> bimestre = cq.from(Bimestre.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bimestre, entity), cb.isNotEmpty(bimestre.get(Bimestre_.notaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Nota> findNotaList(Bimestre entity) {
        Bimestre mergedEntity = this.getMergedEntity(entity);
        List<Nota> notaList = mergedEntity.getNotaList();
        notaList.size();
        return notaList;
    }
    
}
