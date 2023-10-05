package br.com.fiap.petshop.domain.resources;

import br.com.fiap.petshop.domain.entity.Documento;
import br.com.fiap.petshop.domain.service.DocumentoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;


@Path("/documento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DocumentoResource implements Resource<Documento, Long> {


    @Context
    UriInfo uriInfo;


    DocumentoService service = DocumentoService.build();


    @GET
    @Override
    public Response findAll() {
        List<Documento> all = service.findAll();
        return Response.ok( all ).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam( "id" ) Long id) {
        var documento = service.findById( id );
        if (Objects.isNull( documento )) return Response.status( 404 ).build();
        return Response.ok( documento ).build();

    }


    @POST
    @Override
    public Response persist(Documento documento) {
        var entity = service.persist( documento );
        //Criando a URI da requisição
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI uri = ub.path( String.valueOf( entity.getId() ) ).build();
        return Response.created( uri ).entity( entity ).build();
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") Long id, Documento documento) {
        Documento updated = service.update( id, documento );
        if (Objects.isNull( updated )) return Response.notModified().build();
        return Response.ok( updated ).build();
    }

    @DELETE
    @Override
    public Response delete(Documento documento) {
        var updated = service.delete( documento );
        if (updated) return Response.notModified().build();
        return Response.ok( updated ).build();
    }
}
