package br.com.fiap.petshop.domain.resources;

import br.com.fiap.petshop.domain.entity.Telefone;
import br.com.fiap.petshop.domain.service.TelefoneService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;


@Path("/telefone")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneResource implements Resource<Telefone, Long> {


    @Context
    UriInfo uriInfo;


    TelefoneService service = TelefoneService.build();


    @GET
    @Override
    public Response findAll() {
        List<Telefone> all = service.findAll();
        return Response.ok( all ).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam( "id" ) Long id) {
        var telefone = service.findById( id );
        if (Objects.isNull( telefone )) return Response.status( 404 ).build();
        return Response.ok( telefone ).build();

    }


    @POST
    @Override
    public Response persist(Telefone telefone) {
        var entity = service.persist( telefone );
        //Criando a URI da requisição
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI uri = ub.path( String.valueOf( entity.getId() ) ).build();
        return Response.created( uri ).entity( entity ).build();
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") Long id, Telefone telefone) {
        Telefone updated = service.update( id, telefone );
        if (Objects.isNull( updated )) return Response.notModified().build();
        return Response.ok( updated ).build();
    }

    @DELETE
    @Override
    public Response delete(Telefone telefone) {
        var updated = service.delete( telefone );
        if (updated) return Response.notModified().build();
        return Response.ok( updated ).build();
    }
}
