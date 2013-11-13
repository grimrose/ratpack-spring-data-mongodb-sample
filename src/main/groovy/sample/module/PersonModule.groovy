package sample.module

import com.google.inject.AbstractModule
import com.google.inject.spring.SpringIntegration
import org.springframework.beans.factory.BeanFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import sample.model.PersonService
import sample.model.repository.PersonRepository

class PersonModule extends AbstractModule {

    @Override
    protected void configure() {
        ApplicationContext context = new ClassPathXmlApplicationContext("mongoConfig.xml")
        bind(BeanFactory).toInstance(context);

        bind(PersonRepository).toProvider(SpringIntegration.fromSpring(PersonRepository, 'personRepository'))
        bind(PersonService)
    }

}
