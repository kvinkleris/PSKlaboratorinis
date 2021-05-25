package lt.vu.persistence;

import lt.vu.entities.Tree;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Alternative
public class BigTreesDAO extends TreesDAO implements InterfaceTreesDAO {

    @Inject
    EntityManager em;

    @Override
    public List<Tree> loadAll() {
        return em
                .createQuery("select t from Tree t where t.description='big'", Tree.class)
                .getResultList();
    }

    @Override
    public void persist(Tree tree){
        tree.setDescription("big");
        this.em.persist(tree);
    }
}