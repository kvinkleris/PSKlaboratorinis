

package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.TreeMapper;
import lt.vu.mybatis.model.Tree;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class TreesMyBatis {
    @Inject
    private TreeMapper treeMapper;

    @Getter
    private List<Tree> allTrees;

    @Getter @Setter
    private Tree treeToCreate = new Tree();

    @PostConstruct
    public void init() {
        this.loadAllTrees();
    }

    private void loadAllTrees() {
        this.allTrees = treeMapper.selectAll();
    }

    @Transactional
    public String createTree() {
        treeMapper.insert(treeToCreate);
        return "/myBatis/teams?faces-redirect=true";
    }
}

