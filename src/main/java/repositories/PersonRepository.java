package repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import models.Person;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {
}
