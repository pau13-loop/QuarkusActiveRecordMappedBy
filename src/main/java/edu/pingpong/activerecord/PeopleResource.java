package edu.pingpong.activerecord;

import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.pingpong.activerecord.domain.People;
import edu.pingpong.activerecord.service.ServicePeople;

@Path("/people")
public class PeopleResource {

    @Inject
    ServicePeople service;

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Welcome to the Rock Bands History";
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Set<People> listPeople() {
        return service.listPeople();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Set<People> addPeople(@Valid People people) {
        service.addPeople(people);
        return service.listPeople();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Set<People> deletePeople(@Valid People people) {
        service.removePeople(people.getName());
        return service.listPeople();
    }

    @GET
    @Path("/{name}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("name") String name) {
        Optional<People> people = service.getPeople(name);
        return people.isPresent()?
        Response.status(Response.Status.OK).entity(people.get()).build() :
        Response.status(Response.Status.NOT_FOUND).build();
    }
}