package com.github.diogocerqueiralima.authorizationserver.services;

import UserService.proto.UserCreate;
import UserService.proto.UserResponse;
import UserService.proto.UserServiceGrpc;
import com.github.diogocerqueiralima.authorizationserver.model.User;
import io.grpc.stub.StreamObserver;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserServiceGrpc.UserServiceBlockingStub userServiceStub;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserServiceGrpc.UserServiceBlockingStub userServiceStub, PasswordEncoder passwordEncoder) {
        this.userServiceStub = userServiceStub;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(String username, String email, String password, List<User.Role> roles) {

        UserCreate request = UserCreate.newBuilder()
                .setUsername(username)
                .setEmail(email)
                .setPassword(passwordEncoder.encode(password))
                .addAllRoles(roles.stream().map(Enum::name).toList())
                .build();

        UserResponse userResponse = userServiceStub.create(request);

        return new User(
                userResponse.getUsername(),
                userResponse.getPassword(),
                userResponse.getRolesList().stream()
                        .map(User.Role::valueOf)
                        .collect(Collectors.toSet())
        );
    }


}
