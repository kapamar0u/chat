/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBucked;

import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Kosta
 */
@ServerEndpoint("/hello")
public class HelloEndPoint {

    private SessionRegistry registry = SessionRegistry.getInstance();

    @OnOpen
    public void open(Session session, EndpointConfig conf) throws IOException {
        registry.add(session);
        session.getBasicRemote().sendText("hellooo");
    }

    @OnMessage
    public void onMessage(String message, Session sender) {
        Set<Session> sessions = registry.getAll();
        for (Session s : sessions) {
            try {
                s.getBasicRemote().sendText(message);
            } catch (IOException ex) {
                Logger.getLogger(HelloEndPoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @OnClose
    public void close(Session session) {
        registry.remove(session);
    }
}
