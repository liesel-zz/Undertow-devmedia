package br.com.devmedia;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExampleServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private String initMessage; 
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write("Messagem recebida como parametro inicial: "+this.initMessage);
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.initMessage = config.getInitParameter("message");
	}
}
