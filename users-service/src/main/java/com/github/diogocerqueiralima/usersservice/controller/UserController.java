package com.github.diogocerqueiralima.usersservice.controller;

import UserService.proto.*;
import com.github.diogocerqueiralima.usersservice.exceptions.UserAlreadyExistsException;
import com.github.diogocerqueiralima.usersservice.exceptions.UserNotFoundException;
import com.github.diogocerqueiralima.usersservice.model.User;
import com.github.diogocerqueiralima.usersservice.services.UserService;
import com.google.protobuf.Timestamp;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

import java.util.stream.Collectors;

@GrpcService
public class UserController extends UserServiceGrpc.UserServiceImplBase {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void getByUsername(UsernameLookupRequest request, StreamObserver<UserResponse> responseObserver) {

        try {

            User user = userService.getByUsername(request.getUsername());
            UserResponse response = mapUserToResponse(user);

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (UserNotFoundException e) {
            responseObserver.onError(new StatusException(Status.NOT_FOUND.withDescription(e.getMessage())));
        } catch (Exception e) {
            responseObserver.onError(new StatusException(Status.INTERNAL.withDescription(e.getMessage())));
        }

    }

    /**
     *
     * Creates and persists a new user
     *
     * @param request the data to create the user
     * @param responseObserver the observer to send the response when it is created
     *
     */
    @Override
    public void create(UserCreate request, StreamObserver<UserResponse> responseObserver) {

        try {

            User user = userService.create(
                    request.getFirstName(), request.getLastName(), request.getUsername(), request.getEmail(),
                    request.getPassword(),
                    request.getRolesList().stream()
                            .map(User.Role::valueOf)
                            .collect(Collectors.toSet())
            );

            UserResponse response = mapUserToResponse(user);

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (UserAlreadyExistsException e) {
            responseObserver.onError(new StatusException(Status.ALREADY_EXISTS.withDescription(e.getMessage())));
        } catch (Exception e) {
            responseObserver.onError(new StatusException(Status.INTERNAL.withDescription(e.getMessage())));
        }

    }

    @Override
    public void existsById(IdLookupRequest request, StreamObserver<ExistsResponse> responseObserver) {

        responseObserver.onNext(
                ExistsResponse.newBuilder()
                        .setValue(userService.existsById(request.getId()))
                        .build()
        );

        responseObserver.onCompleted();
    }

    private UserResponse mapUserToResponse(User user) {
        return UserResponse.newBuilder()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setCreatedAt(
                        Timestamp.newBuilder()
                                .setNanos(user.getCreatedAt().getNano())
                                .build()
                )
                .addAllRoles(
                        user.getRoles().stream()
                                .map(User.Role::name)
                                .toList()
                )
                .build();
    }

}
