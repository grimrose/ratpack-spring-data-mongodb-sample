package sample.model.repository

import org.springframework.data.mongodb.repository.MongoRepository
import sample.model.Person

interface PersonRepository extends MongoRepository<Person, String> {

    Person findByFirstName(String firstName)

    List<Person> findByLastName(String lastName)

}
