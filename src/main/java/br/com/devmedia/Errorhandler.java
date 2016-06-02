package br.com.devmedia;

import io.undertow.io.Sender;
import io.undertow.server.DefaultResponseListener;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class Errorhandler implements HttpHandler {

	private final HttpHandler next;

	public Errorhandler (final HttpHandler next) {
		this.next = next;
	}

	public void handleRequest(final HttpServerExchange exchange) throws Exception {
		exchange.addDefaultResponseListener(new DefaultResponseListener() {

			public boolean handleDefaultResponse(HttpServerExchange exchange) {
				if (!exchange.isResponseChannelAvailable()) {
					return false;
				}
				exchange.setStatusCode(500);
				if (exchange.getStatusCode() == 500){
					final String errorPage = "<html><head><title>Error</title></head><body>Internal Error</body></html>";
					exchange.getResponseHeaders().put(Headers.CONTENT_LENGTH, "" + errorPage.length());
					exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
					Sender sender = exchange.getResponseSender();
					sender.send(errorPage);
					return true;
				}
				return false;
			}
			
		});
		
		this.next.handleRequest(exchange);
	}
}
