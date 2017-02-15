package mobi.openddr.examples.gwtcanvasdemo.module.client;

import mobi.openddr.examples.gwtcanvasdemo.module.client.ui.ListPersonsView;
import mobi.openddr.examples.gwtcanvasdemo.module.client.ui.PersonDetailView;
import mobi.openddr.examples.gwtcanvasdemo.module.client.ui.WelcomeView;
import com.github.apetrelli.gwtintegration.mvp.client.ui.HasBody;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Container of client components.
 *
 */
public interface ClientFactory {
	
	EventBus getEventBus();
	
	PlaceController getPlaceController();
	
	HasBody getTemplate();

	WelcomeView getWelcomeView();
	
	ListPersonsView getListPersonsView();
	
	PersonDetailView getPersonDetailView();
}
