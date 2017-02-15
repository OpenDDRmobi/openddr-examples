package mobi.openddr.examples.gwtcanvasdemo.module.client.ui;

import com.github.apetrelli.gwtintegration.mvp.client.ui.HasBody;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class gwtcanvasdemoTemplate extends Composite implements HasBody {

	private static gwtcanvasdemoTemplateUiBinder uiBinder = GWT
			.create(gwtcanvasdemoTemplateUiBinder.class);
	@UiField SimplePanel body;

	interface gwtcanvasdemoTemplateUiBinder extends
			UiBinder<Widget, gwtcanvasdemoTemplate> {
	}

	public gwtcanvasdemoTemplate() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	public AcceptsOneWidget getBody() {
		return body;
	}
}
