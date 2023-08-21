package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import models.Person;
import repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PersonService {

    @Inject
    PersonRepository personRepository;

    public Person save(Person person){
        personRepository.persist(person);
        return person;
    }

    public List<Person> findall(){
        return personRepository.findAll().list();
    }

    public Optional<Person> findById(Long id){
        return personRepository.findByIdOptional(id);
    }

    public boolean isEmpty(){
        return personRepository.count() == 0 ? true : false;
    }

    public void delete(Long id){
        personRepository.deleteById(id);
    }

}
