package net.ere.tmp.maven_gwt.client;

import java.util.Date;
import java.util.logging.Logger;

import net.ere.tmp.maven_gwt.shared.AppService;
import net.ere.tmp.maven_gwt.shared.AppServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class App implements EntryPoint {

	private Logger log = Logger.getLogger(App.class.getName());
	private AppServiceAsync appService = GWT.create(AppService.class);

	@Override
	public void onModuleLoad() {
		final Button button = new Button("Get Time");
		final Label labelLocalTime = new Label();
		final Label labelServerTime = new Label();
		labelLocalTime.setStyleName("myLabel");
		labelServerTime.setStyleName("myLabel");
		RootPanel.get().add(button);
		RootPanel.get().add(labelLocalTime);
		RootPanel.get().add(labelServerTime);

		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				log.info("Click ...");

				Date date = new Date();
				labelLocalTime.setText("Localtime:  " + format(date));

				appService.getTime(new AsyncCallback<Date>() {

					@Override
					public void onSuccess(Date result) {
						log.info("Response: " + result);
						labelServerTime.setText("Servertime: " + format(result));
					}

					@Override
					public void onFailure(Throwable caught) {
						log.warning(caught.getMessage());
					}
				});
			}
		});
	}

	private String format(Date date) {
		return DateTimeFormat.getFormat(PredefinedFormat.TIME_MEDIUM).format(date);
	}
}
