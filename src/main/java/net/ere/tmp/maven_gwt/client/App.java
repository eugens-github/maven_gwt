package net.ere.tmp.maven_gwt.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.widget.client.TextButton;

public class App implements EntryPoint {

	@Override
	public void onModuleLoad() {
		final TextButton button = new TextButton("Get Time");
		final Label label = new Label(new Date().toString());
		label.setStyleName("myLabel");
		RootPanel.get().add(button);
		RootPanel.get().add(label);

		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				label.setText(new Date().toString());
			}
		});
	}
}
