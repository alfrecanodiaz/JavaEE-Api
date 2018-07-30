package py.com.testcoding.resources;

import py.com.testcoding.utils.EntityBuilder;
import py.com.testcoding.utils.ResourceUriBuilder;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * Implemented Rest Hypermedia which is root URI should have list of the resources
 *
 * @author Alfredo Cano
 * @since 1.0.0
 */
@Stateless
@Path("/")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class RootResources {

    @Inject
    EntityBuilder entityBuilder;

    @Inject
    ResourceUriBuilder resourceUriBuilder;

    @Context
    UriInfo uriInfo;

    @GET
    public JsonObject getIndex() {
        final URI self = resourceUriBuilder.createResourceUri(RootResources.class, uriInfo);
        final URI message = resourceUriBuilder.createResourceUri(UserResources.class, uriInfo);
        return entityBuilder.buildIndex(self, message);
    }
}