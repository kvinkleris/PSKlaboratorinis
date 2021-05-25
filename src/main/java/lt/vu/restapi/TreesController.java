package lt.vu.restapi;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Tree;
import lt.vu.persistence.TreesDAO;
import lt.vu.restapi.contracts.TreeDto;
import lt.vu.restapi.contracts.Mapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/trees")
public class TreesController {

    @Inject
    @Setter
    @Getter
    private TreesDAO treesDAO;

    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getALl() {
        List<Tree> trees = treesDAO.loadAll();
        if (trees.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<TreeDto> treeDtos = trees.stream()
                .map(Mapper::convertToTreeDto)
                .collect(Collectors.toList());

        return Response.ok(treeDtos).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Tree tree = treesDAO.findOne(id);
        if (tree == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(Mapper.convertToTreeDto(tree)).build();
    }

    @Path("/put/{id}/{name}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") Long treeId,
            @PathParam("name")  String name) {
        try {
            Tree existingTree = treesDAO.findOne(treeId);
            if (existingTree == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingTree.setName(name);
            treesDAO.update(existingTree);
            return Response.ok(Response.Status.OK).build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/post/{name}/{description}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(
            @PathParam("name") final String name,
            @PathParam("description") final String description) {
        Tree newTree = new Tree();
        newTree.setName(name);
        newTree.setDescription(description);
        treesDAO.persist(newTree);
        return Response.ok(Response.Status.OK).build();
    }
}