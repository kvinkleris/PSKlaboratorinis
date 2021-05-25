package lt.vu.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Forest.findAll", query = "select g from Forest as g")
})
public class Forest {
    private Long id;
    private String name;
    private String licencetype;
    private Set<Tree> forestTrees = new HashSet<>();

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

    @Basic
    @Column(name = "LICENCETYPE")
    public String getLicencetype() {
        return licencetype;
    }

    public void setLicencetype(String licencetype) {
        this.licencetype = licencetype;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "FORESTTREE",
            joinColumns = @JoinColumn(name = "FOREST_ID"),
            inverseJoinColumns = @JoinColumn(name = "TREE_ID"))
    public Set<Tree> getForestTrees(){return forestTrees;}
    public void setForestTrees(Set<Tree> forestTrees) {this.forestTrees = forestTrees;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forest forest = (Forest) o;
        return Objects.equals(name, forest.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    }

