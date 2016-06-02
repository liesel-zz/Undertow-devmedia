package br.com.devmedia;

import io.undertow.io.Sender;
import io.undertow.server.DefaultResponseListener;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class MyDefaultResponseListener implements DefaultResponseListener {

	public boolean handleDefaultResponse(HttpServerExchange exchange) {
		System.out.println("boiiissss");
		if (!exchange.isResponseChannelAvailable()) {
			return false;
		}
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

}
