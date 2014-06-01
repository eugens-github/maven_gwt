package net.ere.tmp.maven_gwt.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("app_service")
public interface AppService extends RemoteService {

	Date getTime();
	
	String getPropertyValue(String propertyKey);
}
