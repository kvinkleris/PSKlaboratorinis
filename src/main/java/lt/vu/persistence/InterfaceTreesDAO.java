package lt.vu.persistence;
import lt.vu.entities.Tree;

import java.util.List;
public interface InterfaceTreesDAO {

    List<Tree> loadAll();

    Tree findOne(Long treeId);

    void persist(Tree treeToCreate);

    Tree update(Tree tree);

}
