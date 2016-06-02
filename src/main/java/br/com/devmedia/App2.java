package br.com.devmedia;

import org.xnio.Option;
import org.xnio.OptionMap;

import io.undertow.Undertow;

public class App2 {
	 public static void main(final String[] args) {
		 	MyHandler myHandler = new MyHandler();
		 	Errorhandler errorHandler = new Errorhandler(myHandler);
	        Undertow server = Undertow.builder()
	                .addHttpListener(8080, "localhost").setWorkerOption(org.xnio.Options.WORKER_IO_THREADS, 1)
	                .setHandler(errorHandler).build();
	        server.start();
	    }
}
