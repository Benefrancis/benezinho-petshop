package br.com.fiap.petshop.domain.resources;

import br.com.fiap.petshop.domain.entity.animal.Animal;
import br.com.fiap.petshop.domain.service.AnimalService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;


@Path("/animal")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnimalResource implements Resource<Animal, Long> {


    @Context
    UriInfo uriInfo;


    AnimalService service = AnimalService.build();


    @GET
    @Override
    public Response findAll() {
        List<Animal> all = service.findAll();
        return Response.ok( all ).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam( "id" ) Long id) {
        var animal = service.findById( id );
        if (Objects.isNull( animal )) return Response.status( 404 ).build();
        return Response.ok( animal ).build();

    }


    @POST
    @Override
    public Response persist(Animal animal) {
        var entity = service.persist( animal );
        //Criando a URI da requisição
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI uri = ub.path( String.valueOf( entity.getId() ) ).build();
        return Response.created( uri ).entity( entity ).build();
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") Long id, Animal animal) {
        Animal updated = service.update( id, animal );
        if (Objects.isNull( updated )) return Response.notModified().build();
        return Response.ok( updated ).build();
    }

    @DELETE
    @Override
    public Response delete(Animal animal) {
        var updated = service.delete( animal );
        if (updated) return Response.notModified().build();
        return Response.ok( updated ).build();
    }
}
