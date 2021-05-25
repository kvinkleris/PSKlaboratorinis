package lt.vu.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Tree.findAll", query = "select g from Tree as g")
})
public class Tree {
    private Long id;
    private String name;
    private String description;
    private Set<Forest> forests =new HashSet<>();
    private Integer version;


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

  public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
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

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Column(name = "VERSION")
    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }





    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "FORESTTREE",
            joinColumns = @JoinColumn(name = "TREE_ID"),
            inverseJoinColumns = @JoinColumn(name = "FOREST_ID"))
    public Set<Forest> getForests(){return forests;}
    public void setForests(Set<Forest> forests) {this.forests = forests;}




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return Objects.equals(name, tree.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
