package br.com.devmedia;

import javax.servlet.ServletException;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletInfo;


public class App 
{
	public static final String appPath = "/devmedia";

    public static void main(final String[] args)  {  
    	try{
    		DeploymentInfo servletBuilder = Servlets.deployment()
        	        .setClassLoader(App.class.getClassLoader())
        	        .setContextPath(appPath)
        	        .setDeploymentName("test.war");

    		
    		ServletInfo servlet1 = Servlets.servlet("MessageServlet", ExampleServlet.class);
    		servlet1.addInitParam("message", "Hello World");
    		servlet1.addMapping("/myservlet1"); 
    		servletBuilder.addServlets(servlet1);
    		
    		ServletInfo servlet2 = Servlets.servlet("MyServlet", ExampleServlet.class);
    		servlet2.addInitParam("message", "MyServlet");
    		servlet2.addMapping("/myservlet2");
    		servletBuilder.addServlets(servlet2);

        	DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
        	manager.deploy();
        	PathHandler path = Handlers.path(Handlers.redirect(appPath))
        	        .addPrefixPath(appPath, manager.start());
        	
        	Undertow server = Undertow.builder()
        	        .addHttpListener(8080, "localhost")
        	        .setHandler(path)
        	        .build();
        	server.start();
    	}catch (ServletException e) {
			e.printStackTrace();
		}
    }
}
