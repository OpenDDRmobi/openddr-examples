package mobi.openddr.examples.gwtcanvasdemo.module.client.mvp.activity;

import mobi.openddr.examples.gwtcanvasdemo.module.client.ClientFactory;
import mobi.openddr.examples.gwtcanvasdemo.module.client.requestfactory.ApplicationRequestFactory;
import com.github.apetrelli.gwtintegration.mvp.client.ui.impl.AbstractPresenter;

public abstract class BaseActivity extends AbstractPresenter {

	protected ApplicationRequestFactory requestFactory;
	protected ClientFactory clientFactory;

	public BaseActivity(ApplicationRequestFactory requestFactory,
			ClientFactory clientFactory) {
		super(clientFactory.getPlaceController());
		this.requestFactory = requestFactory;
		this.clientFactory = clientFactory;
	}

}