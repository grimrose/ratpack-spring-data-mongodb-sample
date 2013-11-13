package sample.model

import com.google.inject.Inject
import sample.model.repository.PersonRepository

class PersonService {

    @Inject
    PersonRepository repository

    Person findByFirstName(String firstName) {
        repository.findByFirstName(firstName)
    }

    List<Person> all() {
        repository.findAll()
    }

    List<Person> findByLastName(String lastName) {
        repository.findByLastName(lastName)
    }

    Person save(String firstName, String lastName) {
        repository.save(new Person(firstName: firstName, lastName: lastName))
    }

}
