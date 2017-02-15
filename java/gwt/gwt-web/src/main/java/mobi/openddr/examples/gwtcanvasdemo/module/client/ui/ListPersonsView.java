package mobi.openddr.examples.gwtcanvasdemo.module.client.ui;

import java.util.List;

import mobi.openddr.examples.gwtcanvasdemo.module.client.requestfactory.proxy.PersonProxy;
import com.github.apetrelli.gwtintegration.mvp.client.ui.MvpView;

public interface ListPersonsView extends MvpView<ListPersonsView.Presenter> {
	
	void setPersons(List<PersonProxy> persons);
	
	public interface Presenter extends MvpView.Presenter {
	}
}
