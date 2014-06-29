package net.ere.tmp.maven_gwt.shared;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import net.ere.tmp.maven_gwt.spring.model.Author;

@RemoteServiceRelativePath("app_service")
public interface AppService extends RemoteService {

	Date getTime();
	
	String getPropertyValue(String propertyKey);

    void createAuthor(String name, String blogTitle, String blogText);

    List<BlogItem> getAllBlogItem();
}
