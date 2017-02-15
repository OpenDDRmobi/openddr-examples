package mobi.openddr.examples.gwtcanvasdemo.model.repository;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import mobi.openddr.examples.gwtcanvasdemo.model.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

	@QueryHints(value = { @QueryHint(name = "org.hibernate.readOnly", value = "true")})
	Person findById(Long id);
}
