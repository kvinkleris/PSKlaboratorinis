package lt.vu.restapi.contracts;

import lt.vu.entities.Tree;
import lt.vu.entities.Forest;

import java.util.stream.Collectors;

public class Mapper {
    public static TreeDto convertToTreeDto(Tree tree) {
        if (tree != null) {
            TreeDto treeDto = new TreeDto();
            treeDto.setName(tree.getName());
            treeDto.setDescription(tree.getDescription());
            treeDto.setForests(
                    tree.getForests().stream()
                            .map(Mapper::convertToForestDto)
                            .collect(Collectors.toList())
            );
            return treeDto;
        }
        return null;
    }

    public static ForestDto convertToForestDto(Forest forest) {
        if (forest != null) {
            ForestDto forestDto = new ForestDto();
            forestDto.setName(forest.getName());
            forestDto.setLicenceType(forest.getLicencetype());
            return forestDto;
        }
        return null;
    }
}
