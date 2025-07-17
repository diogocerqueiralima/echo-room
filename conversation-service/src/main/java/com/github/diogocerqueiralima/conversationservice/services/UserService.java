package com.github.diogocerqueiralima.conversationservice.services;

import UserService.proto.ExistsResponse;
import UserService.proto.IdLookupRequest;
import UserService.proto.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserServiceGrpc.UserServiceStub userServiceStub;

    public UserService(UserServiceGrpc.UserServiceStub userServiceStub) {
        this.userServiceStub = userServiceStub;
    }

    public Mono<Boolean> existsById(long id) {
        return Mono.create(sink -> {

            userServiceStub.existsById(
                    IdLookupRequest.newBuilder()
                            .setId(id)
                            .build(),
                    new StreamObserver<>() {

                        @Override
                        public void onNext(ExistsResponse existsResponse) {
                            sink.success(existsResponse.getValue());
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            sink.error(throwable);
                        }

                        @Override
                        public void onCompleted() {}

                    }
            );

        });
    }

}
