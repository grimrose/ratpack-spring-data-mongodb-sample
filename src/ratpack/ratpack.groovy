import sample.model.PersonService
import sample.module.PersonModule

import static groovy.json.JsonOutput.toJson

import ratpack.http.MediaType
import ratpack.groovy.templating.TemplatingModule
import static ratpack.groovy.Groovy.*

ratpack {

    modules {
        register new PersonModule()
        get(TemplatingModule).staticallyCompile = true
        get(TemplatingModule).reloadable = true
    }

    handlers {
        get {
            render groovyTemplate("index.html", title: "My Ratpack App")
        }

        get('hoge') {
            response.send 'get hoge'
        }

        get('hoge/:params') {
            response.send "recieved param is ${pathTokens.params}"
        }

        get('person') { PersonService personService ->
            def firstName = request.queryParams?.firstName
            def lastName = request.queryParams?.lastName
            if (lastName) {
                return response.send(MediaType.APPLICATION_JSON, toJson(personService.findByLastName(lastName)))
            }
            if (!firstName && !lastName) {
                return response.send(MediaType.APPLICATION_JSON, toJson(personService.all()))
            }
            response.send MediaType.APPLICATION_JSON, toJson(personService.findByFirstName(firstName))
        }

        post('person/save') { PersonService personService ->
            def firstName = request.form.firstName
            def lastName = request.form.lastName
            response.send(MediaType.APPLICATION_JSON, toJson(personService.save(firstName, lastName)))
        }

        assets "public"
    }

}
