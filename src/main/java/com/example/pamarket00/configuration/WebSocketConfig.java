package com.example.pamarket00.configuration;

import com.example.pamarket00.handler.UserHandshakeHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    // STOMP (Simple Text Oriented Messaging Protocol) 엔드포인트를 등록하고 설정하는 코드
    //"/our-websocket"은 STOMP 엔드포인트의 URL을 의미합니다. 이 엔드포인트는 웹 소켓을 사용할 수 있는 곳으로 접속할 수 있음

    //"setHandshakeHandler" 메소드는 STOMP 연결이 시작될 때 실행될 전용 핸드셰이크 핸들러를 설정.
    // 이 핸들러는 연결 전에 서버와 클라이언트 사이의 인증과 같은 일련의 절차를 수행할 수 있습니다.

    //"withSockJS" 메소드는 SockJS를 사용할 수 있도록 설정. SockJS는 웹 소켓을 지원하지 않는 브라우저에서도 웹 소켓 기능을 사용할 수 있게 해주는 프레임워크
    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/our-websocket")
                .setHandshakeHandler(new UserHandshakeHandler())
                .withSockJS();
    }


//이 코드는 Spring WebSocket 프레임워크에서 사용할 수 있는 메시지 브로커를 설정하는 코드

//"enableSimpleBroker" 메소드는 간단한 메시지 브로커를 사용할 수 있도록 설정합니다.
// 이 메소드의 인자로 전달된 "/topic"은 메시지가 전달될 수 있는 주제의 접두사를 의미.
// 이 접두사가 적용된 주제는 서버에서 구독을 할 수 있고, 클라이언트에서도 전송을 할 수 있습니다.

//"setApplicationDestinationPrefixes" 메소드는 애플리케이션의 대상 주소 접두사를 설정.
// 이 접두사가 적용된 주소는 애플리케이션의 컨트롤러에서 사용할 수 있습니다.
// 컨트롤러는 이 접두사가 적용된 주소로 메시지를 전송할 수 있고, 클라이언트는 이 접두사가 적용된 주소로 메시지를 구독.

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/ws");
    }

    //어플리케이션 내부에서 사용할 PATH 지정

}
