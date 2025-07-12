package com.github.diogocerqueiralima.usersservice.services;

import UserService.proto.UserCreate;
import UserService.proto.UserResponse;
import UserService.proto.UserServiceGrpc;
import UserService.proto.UsernameLookupRequest;
import com.github.diogocerqueiralima.usersservice.model.User;
import com.github.diogocerqueiralima.usersservice.repositories.UserRepository;
import com.google.protobuf.Timestamp;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

import java.util.stream.Collectors;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void getByUsername(UsernameLookupRequest request, StreamObserver<UserResponse> responseObserver) {

        userRepository.findByUsername(request.getUsername()).ifPresentOrElse(user -> {

            UserResponse response = mapUserToResponse(user);

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        }, () -> responseObserver.onError(new StatusException(Status.NOT_FOUND.withDescription("User not found"))));

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

        if (userRepository.existsByUsernameOrEmail(request.getUsername(), request.getEmail())) {
            responseObserver.onError(
                    new StatusException(
                            Status.ALREADY_EXISTS.withDescription("User with username or email already exists")
                    )
            );
            return;
        }

        User user = userRepository.save(
                new User(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getUsername(),
                        request.getEmail(),
                        request.getPassword(),
                        request.getRolesList().stream()
                                .map(User.Role::valueOf)
                                .collect(Collectors.toSet())
                )
        );

        UserResponse response = mapUserToResponse(user);

        responseObserver.onNext(response);
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
