package controllers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Person;
import services.PersonService;

import java.util.List;
import java.util.Optional;

@Path("/api/customer")
public class PersonController {

    @Inject
    PersonService personService;

    @POST
    @Transactional
    public Person save(Person person){
        return personService.save(person);
    }

    @GET
    public List<Person> geAll(){
        return personService.findall();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id")Long id){

        if(personService.isEmpty()){
            return Response.ok("Person was not found").type(MediaType.APPLICATION_JSON_TYPE).build();
        }
        return Response.ok(personService.findById(id)).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id")Long id, Person person){
        Optional<Person> opPerson = personService.findById(id);

        if(opPerson.isEmpty()){
            return Response.ok("Person was not found").type(MediaType.APPLICATION_JSON_TYPE).build();
        }
        opPerson.get().setId(id);
        opPerson.get().setName(person.getName());
        opPerson.get().setAge(person.getAge());
        opPerson.get().setEmail(person.getEmail());
        opPerson.get().setMaritalStatus(person.getMaritalStatus());
        opPerson.get().setLastName(person.getLastName());

        return Response.ok(personService.save(opPerson.get())).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deletePerson(@PathParam("id") Long id){
        Optional<Person> opPerson = personService.findById(id);

        if(opPerson.isEmpty()){
            return Response.ok("Person was not found").type(MediaType.APPLICATION_JSON_TYPE).build();
        }

        personService.delete(id);

        return Response.noContent().build();

    }



}
