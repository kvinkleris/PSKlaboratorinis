package lt.vu.persistence;

import lt.vu.entities.Tree;
import javax.transaction.Transactional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class TreesDAO implements InterfaceTreesDAO {

    @Inject
    private EntityManager em;
    @Override
    public List<Tree> loadAll() {
        return em.createNamedQuery("Tree.findAll", Tree.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    @Override
    public void persist(Tree tree){
        this.em.persist(tree);
    }

    public Tree findOne(Long id) {
        return em.find(Tree.class, id);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Tree update(Tree tree){
        tree=em.merge(tree);
         em.flush();
         return tree;
    }
}
