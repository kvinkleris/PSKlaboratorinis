

package lt.vu.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "TreeType.findAll", query = "select t from TreeType as t")
})
public class TreeType implements Serializable {

    private Long id;
    private String name;
    public Set<Tree> treesOfType=new HashSet<>();

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "FORESTTYPETREE",
            joinColumns = @JoinColumn(name = "TREETYPE_ID"),
            inverseJoinColumns = @JoinColumn(name = "TREE_ID"))
    public Set<Tree> gettreesOfType(){
        return treesOfType;}
    public void settreesOfType(Set<Tree> treesOfType) {this.treesOfType = treesOfType;}




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeType treetype = (TreeType) o;
        return Objects.equals(name, treetype.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    public int treeCount()
    {
        return treesOfType.size();
    }
}