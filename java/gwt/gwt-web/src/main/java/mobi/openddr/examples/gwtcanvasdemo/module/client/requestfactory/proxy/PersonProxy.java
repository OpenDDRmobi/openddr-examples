package mobi.openddr.examples.gwtcanvasdemo.module.client.requestfactory.proxy;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import mobi.openddr.examples.gwtcanvasdemo.model.domain.Person;
import mobi.openddr.examples.gwtcanvasdemo.module.server.locator.PersonLocator;
import mobi.openddr.examples.gwtcanvasdemo.shared.shared.enums.PersonalTitle;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = Person.class, locator = PersonLocator.class)
public interface PersonProxy extends EntityProxy {
	
	Long getId();
	
	Integer getVersion();
	
	@NotNull
	PersonalTitle getPersonalTitle();
	
	void setPersonalTitle(PersonalTitle personalTitle);
	
	@NotEmpty
	String getFirstName();
	
	void setFirstName(String firstName);
	
	@NotEmpty
	String getLastName();
	
	void setLastName(String lastName);
	
	Date getBirthDate();
	
	void setBirthDate(Date birthDate);
	
	BigDecimal getAnnualIncome();
	
	void setAnnualIncome(BigDecimal annualIncome);
}
