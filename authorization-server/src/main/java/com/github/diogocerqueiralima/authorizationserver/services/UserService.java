package com.github.diogocerqueiralima.authorizationserver.services;

import UserService.proto.UserCreate;
import UserService.proto.UserResponse;
import UserService.proto.UserServiceGrpc;
import com.github.diogocerqueiralima.authorizationserver.exceptions.AgreementException;
import com.github.diogocerqueiralima.authorizationserver.exceptions.PasswordMatchException;
import com.github.diogocerqueiralima.authorizationserver.exceptions.UserAlreadyExistsException;
import com.github.diogocerqueiralima.authorizationserver.model.User;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
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

    public User create(
            String firstName, String lastName, String username, String email, String password, String confirmPassword,
            boolean agreement, List<User.Role> roles
    ) {

        if (!agreement)
            throw new AgreementException();

        if (!password.equals(confirmPassword))
            throw new PasswordMatchException();

        UserCreate request = UserCreate.newBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUsername(username)
                .setEmail(email)
                .setPassword(passwordEncoder.encode(password))
                .addAllRoles(roles.stream().map(Enum::name).toList())
                .build();

        try {

            UserResponse userResponse = userServiceStub.create(request);

            return new User(
                    userResponse.getUsername(),
                    userResponse.getPassword(),
                    userResponse.getRolesList().stream()
                            .map(User.Role::valueOf)
                            .collect(Collectors.toSet())
            );

        } catch (StatusRuntimeException e) {

            if (e.getStatus().getCode() == Status.Code.ALREADY_EXISTS)
                throw new UserAlreadyExistsException();

            throw e;
        }

    }


}
