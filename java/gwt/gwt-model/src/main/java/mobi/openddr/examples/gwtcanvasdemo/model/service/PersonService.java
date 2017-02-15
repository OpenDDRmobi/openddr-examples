package mobi.openddr.examples.gwtcanvasdemo.model.service;

import java.util.List;

import mobi.openddr.examples.gwtcanvasdemo.model.domain.Person;
import com.github.apetrelli.gwtintegration.service.CrudService;

public interface PersonService extends CrudService<Person, Long> {

	long countAllPersons();
	
	List<Person> findAllPersons();
}
