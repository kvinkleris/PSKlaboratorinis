package lt.vu.persistence;

import lt.vu.entities.Forest;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class ForestsDAO {

    @Inject
    private EntityManager em;

    public void persist(Forest forest){
        this.em.persist(forest);
    }
    public void setEm(EntityManager em) {
        this.em = em;
    }


    public List<Forest> loadAll() {
        return em.createNamedQuery("Forest.findAll", Forest.class).getResultList();
    }

    public Forest findOne(Long id){
        return em.find(Forest.class, id);
    }

    public Forest update(Forest forest){
        return em.merge(forest);
    }
}
