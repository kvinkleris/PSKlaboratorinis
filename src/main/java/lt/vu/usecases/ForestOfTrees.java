package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Forest;
import lt.vu.entities.Tree;
import lt.vu.persistence.ForestsDAO;
import lt.vu.persistence.TreesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
public class ForestOfTrees implements Serializable {

    @Inject
    private ForestsDAO forestsDAO;

    @Inject
    private TreesDAO treesDAO;

    @Getter
    @Setter
    private Forest forest;

    @Getter @Setter
    private Tree treeToCreate = new Tree();

    @Getter
    private List<Tree> allTrees;



    @PostConstruct
    public void init() {

        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long forestId = Long.parseLong(requestParameters.get("forestId"));
        System.out.println(forestId);
        this.forest = forestsDAO.findOne(forestId);
    }

    @Transactional
    public String createTree() {
        treeToCreate.getForests().add(this.forest);
        treesDAO.persist(treeToCreate);
        return "secondforestpage?faces-redirect=true&forestId=" + this.forest.getId();
    }

}


