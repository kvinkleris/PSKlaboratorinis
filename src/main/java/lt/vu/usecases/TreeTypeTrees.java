package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.*;
import lt.vu.persistence.*;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Forest;
import lt.vu.entities.Tree;
import lt.vu.entities.TreeType;
import lt.vu.persistence.ForestsDAO;
import lt.vu.persistence.TreesDAO;
import lt.vu.persistence.TreeTypeDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
public class TreeTypeTrees implements Serializable {

    @Inject
    private TreeTypeDAO treetypesDAO;

    @Inject
    private TreesDAO treesDAO;

    @Getter
    @Setter
    private TreeType treeType;

    @Getter @Setter
    private Tree treeToCreate = new Tree();

    @Getter
    private List<Tree> allTrees;

    @Getter @Setter
    private Long requiredtreeID;



    @PostConstruct
    public void init() {

        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long treetypeId = Long.parseLong(requestParameters.get("treetypeId"));
        this.treeType = treetypesDAO.findOne(treetypeId);
    }

    @Transactional
    public String addTree() {
        Tree tree = treesDAO.findOne(requiredtreeID);
        treeType.gettreesOfType().add(tree);
        treetypesDAO.update(treeType);
        return "forestpage?faces-redirect=true&treetypeId=" + this.treeType.getId();
    }

}



