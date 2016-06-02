package br.com.devmedia;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class MyHandler implements HttpHandler{

	public void handleRequest(HttpServerExchange exchange) throws Exception {
		exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        exchange.getResponseSender().send("Hello World "+exchange.getRequestPath());
	}

}
