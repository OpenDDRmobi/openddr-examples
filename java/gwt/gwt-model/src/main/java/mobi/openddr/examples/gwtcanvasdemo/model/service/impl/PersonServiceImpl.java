package mobi.openddr.examples.gwtcanvasdemo.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.apetrelli.gwtintegration.datajpa.AbstractCrudService;
import mobi.openddr.examples.gwtcanvasdemo.model.domain.Person;
import mobi.openddr.examples.gwtcanvasdemo.model.repository.PersonRepository;
import mobi.openddr.examples.gwtcanvasdemo.model.service.PersonService;

@Service
@Transactional(readOnly=true)
public class PersonServiceImpl extends AbstractCrudService<Person, Long, PersonRepository> implements PersonService{
	
	private final Sort defaultSort = new Sort("lastName");
	
	@Autowired
	public PersonServiceImpl(PersonRepository repository) {
		super(repository);
	}
	
	public long countAllPersons() {
		return repository.count();
	}

	public List<Person> findAllPersons() {
		return repository.findAll(defaultSort);
	}
}
