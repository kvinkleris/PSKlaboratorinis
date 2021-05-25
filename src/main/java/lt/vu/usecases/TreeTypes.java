package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.TreeType;
import lt.vu.persistence.TreeTypeDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class TreeTypes {

    @Inject
    private TreeTypeDAO treetypeDAO;

    @Getter
    @Setter
    private TreeType treeTypeToCreate = new TreeType();

    @Getter
    private List<TreeType> allTreeTypes;

    @PostConstruct
    public void init()
    {
        loadAllTreeTypes();
    }

    @Transactional
    public String createTreeType()
    {
        this.treetypeDAO.persist(treeTypeToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllTreeTypes() {
        allTreeTypes = treetypeDAO.loadAll();
    }
}