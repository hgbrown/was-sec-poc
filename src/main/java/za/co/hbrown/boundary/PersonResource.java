package za.co.hbrown.boundary;

import za.co.hbrown.entity.Person;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA by henryb.
 *
 * @author <a href="mailto:henry.g.brown@gmail.com">henryb</a>
 * @version 0.1201502201029
 * @since 0.1201502201029
 */
@Path("/person")
public class PersonResource {

    private Map<Integer, Person> dataInMemory;

    public PersonResource() {
        dataInMemory = new HashMap<Integer, Person>();
    }

    @RolesAllowed({ "reader", "administrator" })
    @POST
    @Consumes("application/xml")
    public Response savePerson(Person person) {
        System.out.println("POST savePerson: " + person);
        int id = dataInMemory.size() + 1;
        person.setId(id);
        dataInMemory.put(id, person);
        return Response.created(URI.create("/person/" + id)).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        Person person = dataInMemory.get(id);
        if (person == null) {
            // There is not person with this id
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        dataInMemory.remove(id);
        return Response.status(Response.Status.GONE).build();
    }

    @RolesAllowed({ "administrator" })
    @GET
    @Path("{id}")
    @Produces("application/xml")
    public Person findById(@PathParam("id") int id) {
        Person person = dataInMemory.get(id);
        if (person == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return person;
    }
}
