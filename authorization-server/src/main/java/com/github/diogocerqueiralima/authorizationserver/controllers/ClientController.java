package com.github.diogocerqueiralima.authorizationserver.controllers;

import com.github.diogocerqueiralima.authorizationserver.dto.ClientDto;
import com.github.diogocerqueiralima.authorizationserver.dto.CreateClientDto;
import com.github.diogocerqueiralima.authorizationserver.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     *
     * POST endpoint to create a new OAuth2 client
     *
     * @param dto the data used to create the client
     *
     * @return an HTTP response contained the created client public details
     */
    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody @Valid CreateClientDto dto) {

        RegisteredClient registeredClient = clientService.create(
                dto.clientId(), dto.clientSecret(), dto.redirectUris(), dto.scopes()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ClientDto(
                                registeredClient.getClientId(),
                                registeredClient.getRedirectUris().toArray(new String[0]),
                                registeredClient.getClientIdIssuedAt()
                        )
                );
    }

}
