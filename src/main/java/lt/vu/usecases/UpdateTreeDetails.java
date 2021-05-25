package lt.vu.usecases;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Tree;
import lt.vu.persistence.TreesDAO;
import lt.vu.interceptors.LoggedInvocation;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RequestScoped
@Named
@Getter @Setter
public class UpdateTreeDetails implements Serializable,UpdateTreeNameInterface {

    private Tree tree;

    @Inject
    private TreesDAO treesDAO;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long treeId = Long.parseLong(requestParameters.get("treeId"));
        this.tree = treesDAO.findOne(treeId);
    }


      @Override
      @Transactional(Transactional.TxType.REQUIRES_NEW)
        public String updateNameOfTree() throws InterruptedException{
                TimeUnit.SECONDS.sleep(5);
            try{
            treesDAO.update(this.tree);
        } catch (OptimisticLockException e) {
                return "forestpage.xhtml?treeId=" + this.tree.getId() + "&faces-redirect=true"+"&error=optimistic-lock-exception";
        }
                return "forestpage.xhtml?treeId=" + this.tree.getId() + "&faces-redirect=true";
    }
}
