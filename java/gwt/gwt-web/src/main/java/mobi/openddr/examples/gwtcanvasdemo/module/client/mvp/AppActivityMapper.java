package mobi.openddr.examples.gwtcanvasdemo.module.client.mvp;

import mobi.openddr.examples.gwtcanvasdemo.module.client.ClientFactory;
import mobi.openddr.examples.gwtcanvasdemo.module.client.mvp.activity.ListPersonsActivity;
import mobi.openddr.examples.gwtcanvasdemo.module.client.mvp.activity.PersonDetailActivity;
import mobi.openddr.examples.gwtcanvasdemo.module.client.mvp.activity.WelcomeActivity;
import mobi.openddr.examples.gwtcanvasdemo.module.client.mvp.place.ListPersonsPlace;
import mobi.openddr.examples.gwtcanvasdemo.module.client.mvp.place.PersonDetailPlace;
import mobi.openddr.examples.gwtcanvasdemo.module.client.mvp.place.WelcomePlace;
import mobi.openddr.examples.gwtcanvasdemo.module.client.requestfactory.ApplicationRequestFactory;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * Unique implementation of ActivityMapper.
 *
 */
public class AppActivityMapper implements ActivityMapper {
	
	private ClientFactory clientFactory;
	
	private ApplicationRequestFactory requestFactory;

	/**
	 * Constructor of class.
	 * @param clientFactory Factory to determinate view.
	 * @param requestFactory Factory to determinate service to call.
	 */
	public AppActivityMapper(ClientFactory clientFactory, ApplicationRequestFactory requestFactory) {
		this.clientFactory = clientFactory;
		this.requestFactory = requestFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof WelcomePlace) {
			return new WelcomeActivity((WelcomePlace) place, clientFactory);
		} else if (place instanceof ListPersonsPlace) {
			return new ListPersonsActivity(requestFactory, clientFactory);
		} else if (place instanceof PersonDetailPlace) {
			return new PersonDetailActivity((PersonDetailPlace) place, requestFactory, clientFactory);
		}
		return null;
	}

}
