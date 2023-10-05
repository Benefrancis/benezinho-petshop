package br.com.fiap.petshop.domain.resources;

import br.com.fiap.petshop.domain.entity.servico.Servico;
import br.com.fiap.petshop.domain.service.ServicoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;


@Path("/servico")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicoResource implements Resource<Servico, Long> {


    @Context
    UriInfo uriInfo;


    ServicoService service = ServicoService.build();


    @GET
    @Override
    public Response findAll() {
        List<Servico> all = service.findAll();
        return Response.ok( all ).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam( "id" ) Long id) {
        var servico = service.findById( id );
        if (Objects.isNull( servico )) return Response.status( 404 ).build();
        return Response.ok( servico ).build();

    }


    @POST
    @Override
    public Response persist(Servico servico) {
        var entity = service.persist( servico );
        //Criando a URI da requisição
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI uri = ub.path( String.valueOf( entity.getId() ) ).build();
        return Response.created( uri ).entity( entity ).build();
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") Long id, Servico servico) {
        Servico updated = service.update( id, servico );
        if (Objects.isNull( updated )) return Response.notModified().build();
        return Response.ok( updated ).build();
    }

    @DELETE
    @Override
    public Response delete(Servico servico) {
        var updated = service.delete( servico );
        if (updated) return Response.notModified().build();
        return Response.ok( updated ).build();
    }
}
