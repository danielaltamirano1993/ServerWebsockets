package daoc.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@SpringBootApplication
@EnableWebSocket
public class WebSocketServer4AndroidApplication implements WebSocketConfigurer {

	@Autowired
	SaludosHandler saludosHandler;
	
	public static void main(String[] args) {
		SpringApplication.run(WebSocketServer4AndroidApplication.class, args);
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(saludosHandler, "/saludos");
	}

}
