package com.github.diogocerqueiralima.conversationservice.application.routes;

import com.github.diogocerqueiralima.conversationservice.application.handlers.PrivateChatHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.*;

@Component
public class PrivateChatRoute {

    @Bean
    public RouterFunction<ServerResponse> routes(PrivateChatHandler privateChatHandler) {
        return route()
                .POST("/api/v1/chat/private", privateChatHandler::create)
                .GET("/api/v1/chat/private/{id}", privateChatHandler::getById)
                .DELETE("/api/v1/chat/private/{id}", privateChatHandler::deleteById)
                .build();
    }

}
