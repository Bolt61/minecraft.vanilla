package ch.bolt61.vanillaserver.websocket;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

public class WebClient implements StompSessionHandler {
  
  private static final String SERVER_URI = "wss://localhost:8888";
  
  private JavaPlugin plugin;
  
  private final WebSocketStompClient stompClient;
  private StompSession session;

  public WebClient(JavaPlugin plugin, URI serverUri) {
    WebSocketClient client = new StandardWebSocketClient();
    SockJsClient sockJs = new SockJsClient(Arrays.asList(new WebSocketTransport(client)));
    stompClient = new WebSocketStompClient(sockJs);
    stompClient.setMessageConverter(new MappingJackson2MessageConverter());
  }
  
  public void connect() {
    try {
      session = stompClient.connect(SERVER_URI, this).get();
      plugin.getLogger().log(Level.INFO, "Connected with server. URI: " + SERVER_URI);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }
  
  public void subscribe(String topic, StompFrameHandler handler) {
    session.subscribe("test", handler);
  }

  @Override
  public Type getPayloadType(StompHeaders headers) {
    return null;
  }

  @Override
  public void handleFrame(StompHeaders headers, Object payload) {
    
  }

  @Override
  public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
    
  }

  @Override
  public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
      Throwable exception) {
    
  }

  @Override
  public void handleTransportError(StompSession session, Throwable exception) {
    
  }
}
