package ch.bolt61.vanillaserver.websocket;

import java.lang.reflect.Type;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

public class ChatListener implements StompFrameHandler {

  @Override
  public Type getPayloadType(StompHeaders headers) {

    return null;
  }

  @Override
  public void handleFrame(StompHeaders headers, Object payload) {

  }
}
