package py.com.testcoding.resources;

import py.com.testcoding.entities.User;
import py.com.testcoding.utils.ResourceUriBuilder;
import py.com.testcoding.repositories.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 * @author Alfredo Cano
 * @since 1.0.0
 */
@Stateless
@Path("messages")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class UserResources {

    @Inject
    UserRepository userRepository;

    @Inject
    ResourceUriBuilder resourceUriBuilder;

    @Context
    UriInfo uriInfo;

    @GET
    public JsonArray findAll() {
        JsonArrayBuilder list = Json.createArrayBuilder();
        List<User> all = this.userRepository.findAll();
        all.stream()
                .map(m -> m.toJson(
                        resourceUriBuilder.createResourceUri(
                                UserResources.class,
                                "findById",
                                m.getId(),
                                uriInfo
                                )
                        )
                )
                .forEach(list::add);
        return list.build();
    }

    @GET
    @Path("{id}")
    public JsonObject findById(@PathParam("id") Long id) {
        User user = this.userRepository.findById(id);
        final URI self = resourceUriBuilder.createResourceUri(
                UserResources.class, "findById", user.getId(), uriInfo
        );
        return user.toJson(self);
    }

    @POST
    public Response save(@Valid User user) {
        this.userRepository.create(user);
        final URI self = resourceUriBuilder.createResourceUri(
                UserResources.class, "findById", user.getId(), uriInfo
        );
        return Response.created(self).build();
    }
}
