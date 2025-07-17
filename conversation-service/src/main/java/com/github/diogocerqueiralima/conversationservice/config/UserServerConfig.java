package com.github.diogocerqueiralima.conversationservice.config;

import UserService.proto.UserServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class UserServerConfig {

    @Bean
    public UserServiceGrpc.UserServiceStub userServiceStub(GrpcChannelFactory factory) {
        return UserServiceGrpc.newStub(factory.createChannel("users-service"));
    }

}
