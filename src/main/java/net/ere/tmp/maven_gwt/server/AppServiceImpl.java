package net.ere.tmp.maven_gwt.server;

import java.util.Date;
import java.util.logging.Logger;

import net.ere.tmp.maven_gwt.shared.AppService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AppServiceImpl extends RemoteServiceServlet implements AppService {

	private static final long serialVersionUID = 1L;
	
	private Logger log = Logger.getLogger(AppServiceImpl.class.getName());

	@Override
	public Date getTime() {
		log.info("Call getTime()");
		return new Date();
	}

	@Override
	public String getPropertyValue(String propertyKey) {
		log.info("Call getPropertyValue(" + propertyKey + ")");
		return System.getProperty(propertyKey);
	}

}
