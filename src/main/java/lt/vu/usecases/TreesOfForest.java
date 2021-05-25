package lt.vu.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Tree;
import lt.vu.entities.Forest;
import lt.vu.persistence.TreesDAO;
import lt.vu.persistence.ForestsDAO;

@Model
public class TreesOfForest implements Serializable {

    @Inject
    private TreesDAO treesDAO;

    @Inject
    private ForestsDAO forestsDAO;

    @Getter @Setter
    private Tree tree;

    @Getter @Setter
    private Long requiredforestID;

    @Getter
    private List<Forest> allForests;



    @PostConstruct
    public void init() {

        this.loadAllForests();
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long treeId = Long.parseLong(requestParameters.get("treeId"));
        this.tree = treesDAO.findOne(treeId);
    }

    @Transactional
    public String createForest() {
        Forest forest = forestsDAO.findOne(requiredforestID);
        tree.getForests().add(forest);
        treesDAO.update(tree);
        return "forestpage?faces-redirect=true&treeId=" + this.tree.getId();
}

    private void loadAllForests(){
        this.allForests = forestsDAO.loadAll();
    }
}
