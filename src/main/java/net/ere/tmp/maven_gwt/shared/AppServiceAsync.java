package net.ere.tmp.maven_gwt.shared;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AppServiceAsync {

	void getTime(AsyncCallback<Date> callback);

	void getPropertyValue(String propertyKey, AsyncCallback<String> callback);

    void createAuthor(String name, String blogTitle, String blogText, AsyncCallback<Void> async);

    void getAllBlogItem(AsyncCallback<List<BlogItem>> async);
}
