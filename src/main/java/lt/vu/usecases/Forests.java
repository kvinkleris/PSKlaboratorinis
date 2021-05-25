package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Forest;
import lt.vu.persistence.ForestsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model//@Named and @RequestScoped
public class Forests {

    @Inject
    private ForestsDAO forestsDAO;
    @Getter @Setter
    private Forest forestToCreate = new Forest();
    @Getter
    private List<Forest> allForests;

    @PostConstruct
    public void init(){
        loadAllForests();
    }

    @Transactional
    public String createForest(){
        this.forestsDAO.persist(forestToCreate);
        return "index?faces-redirect=true";
    }


    private void loadAllForests(){
        this.allForests = forestsDAO.loadAll();
    }
}

