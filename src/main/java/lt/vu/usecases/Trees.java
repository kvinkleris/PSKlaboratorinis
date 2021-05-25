package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Tree;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.InterfaceTreesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@LoggedInvocation
@Model
public class Trees {

    @Inject
    private InterfaceTreesDAO treesDAO;

    @Getter @Setter
    private Tree treeToCreate = new Tree();

    @Getter
    private List<Tree> allTrees;

    @PostConstruct
    public void init(){
        loadAllTrees();
    }

    @Transactional
    public String createTree(){
        this.treesDAO.persist(treeToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllTrees(){
        this.allTrees = treesDAO.loadAll();
    }
}
