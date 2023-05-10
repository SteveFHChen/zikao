package com.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SystemListener
 *
 */
@WebListener
public class SystemListener implements 
	ServletContextListener, ServletContextAttributeListener, //ServletContext
	ServletRequestListener, ServletRequestAttributeListener, //ServletRequest
	HttpSessionListener, HttpSessionAttributeListener, HttpSessionActivationListener, HttpSessionBindingListener, HttpSessionIdListener, //Session 
	AsyncListener {

	private static Properties prop = new Properties();
	
	public static String get(String params){
		return prop.getProperty(params);
	}
	
    public SystemListener() {
    	System.out.println(this.getClass().getName()+" SystemListener() executing...");
    }

    public void sessionCreated(HttpSessionEvent se)  { 
    	System.out.println(this.getClass().getName()+" sessionCreated() executing...");
    }

    public void attributeRemoved(ServletContextAttributeEvent scae)  { 
    	System.out.println(this.getClass().getName()+" attributeRemoved() executing...");
    }

    public void onError(AsyncEvent arg0) throws java.io.IOException { 
    	System.out.println(this.getClass().getName()+" onError() executing...");
    }

    public void sessionIdChanged(HttpSessionEvent arg0, String arg1)  { 
    	System.out.println(this.getClass().getName()+" sessionIdChanged() executing...");
    }

    public void attributeAdded(ServletRequestAttributeEvent srae)  { 
    	System.out.println(this.getClass().getName()+" attributeAdded() executing...");
    }

    public void onTimeout(AsyncEvent arg0) throws java.io.IOException { 
    	System.out.println(this.getClass().getName()+" onTimeout() executing...");
    }

    public void attributeReplaced(HttpSessionBindingEvent se)  { 
    	System.out.println(this.getClass().getName()+" attributeReplaced() executing...");
    }

    public void sessionWillPassivate(HttpSessionEvent se)  { 
    	System.out.println(this.getClass().getName()+" sessionWillPassivate() executing...");
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println(this.getClass().getName()+" contextInitialized() executing...");
    	loadProperties();
    }
    
    public static void loadProperties() {
    	InputStream inputStream;

    	try {
	    	inputStream = SystemListener.class.getResourceAsStream("/local-application.properties");
	
	    	if(inputStream != null){
	    		prop.load(inputStream);
	    		System.out.println("Reload config success.");
	    	}
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	}
    }

    public void attributeAdded(ServletContextAttributeEvent event)  { 
    	System.out.println(this.getClass().getName()+" attributeAdded() executing...");
    }

    public void onComplete(AsyncEvent arg0) throws java.io.IOException { 
    	System.out.println(this.getClass().getName()+" onComplete() executing...");
    }

    public void requestDestroyed(ServletRequestEvent sre)  { 
    	System.out.println(this.getClass().getName()+" requestDestroyed() executing...");
    }

    public void attributeRemoved(ServletRequestAttributeEvent srae)  { 
    	System.out.println(this.getClass().getName()+" attributeRemoved() executing...");
    }

    public void onStartAsync(AsyncEvent arg0) throws java.io.IOException { 
    	System.out.println(this.getClass().getName()+" onStartAsync() executing...");
    }

    public void requestInitialized(ServletRequestEvent sre)  { 
    	System.out.println(this.getClass().getName()+" requestInitialized() executing...");
    }

    public void valueBound(HttpSessionBindingEvent event)  { 
    	System.out.println(this.getClass().getName()+" valueBound() executing...");
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println(this.getClass().getName()+" sessionDestroyed() executing...");
    }

    public void sessionDidActivate(HttpSessionEvent se)  { 
    	System.out.println(this.getClass().getName()+" sessionDidActivate() executing...");
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println(this.getClass().getName()+" contextDestroyed() executing...");
    }

    public void attributeReplaced(ServletRequestAttributeEvent srae)  { 
    	System.out.println(this.getClass().getName()+" attributeReplaced() executing...");
    }

    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	System.out.println(this.getClass().getName()+" attributeAdded() executing...");
    }

    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	System.out.println(this.getClass().getName()+" attributeRemoved() executing...");
    }

    public void attributeReplaced(ServletContextAttributeEvent event)  { 
    	System.out.println(this.getClass().getName()+" attributeReplaced() executing...");
    }

    public void valueUnbound(HttpSessionBindingEvent event)  { 
    	System.out.println(this.getClass().getName()+" valueUnbound() executing...");
    }
	
}
