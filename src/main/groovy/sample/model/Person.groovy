package sample.model

import groovy.transform.Canonical
import org.springframework.data.annotation.Id

@Canonical
class Person {

    @Id
    String id

    String firstName

    String lastName
}
