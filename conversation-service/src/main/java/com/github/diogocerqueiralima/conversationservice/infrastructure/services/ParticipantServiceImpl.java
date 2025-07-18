package com.github.diogocerqueiralima.conversationservice.infrastructure.services;

import UserService.proto.IdLookupRequest;
import UserService.proto.UserResponse;
import UserService.proto.UserServiceGrpc;
import com.github.diogocerqueiralima.conversationservice.domain.exceptions.ParticipantNotFoundException;
import com.github.diogocerqueiralima.conversationservice.domain.model.Participant;
import com.github.diogocerqueiralima.conversationservice.domain.ports.outbound.ParticipantService;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final UserServiceGrpc.UserServiceStub userServiceStub;

    public ParticipantServiceImpl(UserServiceGrpc.UserServiceStub userServiceStub) {
        this.userServiceStub = userServiceStub;
    }

    @Override
    public Mono<Participant> getById(Long id) {

        return Mono.create(sink -> {

            userServiceStub.getById(
                    IdLookupRequest.newBuilder()
                            .setId(id)
                            .build(),
                    new StreamObserver<>() {
                        @Override
                        public void onNext(UserResponse userResponse) {

                            String name = userResponse.getFirstName() + " " + userResponse.getLastName();

                            Participant participant = new Participant(userResponse.getId(), name);
                            sink.success(participant);
                        }

                        @Override
                        public void onError(Throwable throwable) {

                            if (throwable instanceof StatusException e && e.getStatus().getCode() == Status.Code.NOT_FOUND)
                                sink.error(new ParticipantNotFoundException());

                            sink.error(throwable);
                        }

                        @Override
                        public void onCompleted() {}

                    }
            );

        });

    }

}
