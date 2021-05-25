package lt.vu.persistence;

import lt.vu.entities.Forest;

import javax.enterprise.inject.Specializes;

@Specializes
public class ForestsDAOSpecial extends ForestsDAO {
    @Override
    public Forest update(Forest forest)
    {
        forest.setName(forest.getName()+"special forest");
        return super.update(forest);
    }
}
