package net.ere.tmp.maven_gwt.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AppServiceAsync {

	void getTime(AsyncCallback<Date> callback);

	void getPropertyValue(String propertyKey, AsyncCallback<String> callback);

}
