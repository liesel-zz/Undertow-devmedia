package br.com.devmedia;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExampleServlet1 extends HttpServlet{
	private String message;
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write("Messagem recebida "+message);
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.message = config.getInitParameter("message");
	}

}
