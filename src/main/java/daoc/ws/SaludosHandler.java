package daoc.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
public class SaludosHandler extends TextWebSocketHandler {

    List<WebSocketSession> allSessions = new ArrayList<>();
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        allSessions.add(session);
        System.out.println("Conectado: " + session.toString());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        allSessions.remove(session);
        System.out.println("Desconectado: " + session.toString());
    }
    
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        try {
            TextMessage msg = new TextMessage("Hola, " + message.getPayload() + "!");
            for(WebSocketSession s : allSessions) {
                s.sendMessage(msg);
            }
        } catch (Exception ex) {
            Logger.getLogger(SaludosHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
