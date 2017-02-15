package mobi.openddr.examples.gwtcanvasdemo.module.client.mvp;

import mobi.openddr.examples.gwtcanvasdemo.module.client.mvp.place.ListPersonsPlace;
import mobi.openddr.examples.gwtcanvasdemo.module.client.mvp.place.PersonDetailPlace;
import mobi.openddr.examples.gwtcanvasdemo.module.client.mvp.place.WelcomePlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

/**
 * Mapping all Tokenizer of places.
 *
 */
@WithTokenizers({ WelcomePlace.Tokenizer.class, ListPersonsPlace.Tokenizer.class, PersonDetailPlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {

}