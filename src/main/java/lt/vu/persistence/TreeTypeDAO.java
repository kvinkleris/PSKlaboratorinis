package lt.vu.persistence;

import lt.vu.entities.TreeType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class TreeTypeDAO {

    @Inject
    private EntityManager em;

    public List<TreeType> loadAll()
    {
        return em.createNamedQuery("TreeType.findAll", TreeType.class).getResultList();
    }

    public void setEm(EntityManager em)
    {
        this.em = em;
    }

    public void persist(TreeType treetype)
    {
        em.persist(treetype);
    }

    public TreeType findOne(Long id)
    {
        return em.find(TreeType.class,id);
    }

    public TreeType update(TreeType treeType){
        return em.merge(treeType);
    }
}