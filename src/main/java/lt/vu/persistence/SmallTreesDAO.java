package lt.vu.persistence;

import lt.vu.entities.Tree;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Alternative
public class SmallTreesDAO extends TreesDAO implements InterfaceTreesDAO {

    @Inject
    EntityManager em;

    @Override
    public List<Tree> loadAll() {
        return em
                .createQuery("select t from Tree t where t.description='small'", Tree.class)
                .getResultList();
    }
}