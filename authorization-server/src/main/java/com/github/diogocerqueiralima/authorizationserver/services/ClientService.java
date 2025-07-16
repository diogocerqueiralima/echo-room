package com.github.diogocerqueiralima.authorizationserver.services;

import com.github.diogocerqueiralima.authorizationserver.exceptions.ClientNotFoundException;
import com.github.diogocerqueiralima.authorizationserver.repositories.CustomRegisteredClientRepository;
import org.springframework.grpc.autoconfigure.client.ClientInterceptorsConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    private final CustomRegisteredClientRepository registeredClientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(CustomRegisteredClientRepository registeredClientRepository, PasswordEncoder passwordEncoder, ClientInterceptorsConfiguration clientInterceptorsConfiguration) {
        this.registeredClientRepository = registeredClientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisteredClient getById(UUID id) {

        RegisteredClient registeredClient = registeredClientRepository.findById(id.toString());

        if (registeredClient == null)
            throw new ClientNotFoundException(id);

        return registeredClient;
    }

    /**
     *
     * Get all RegisteredClient in the application
     *
     * @return a list with all RegisteredClient
     */
    public List<RegisteredClient> getAll() {
        return registeredClientRepository.findAll();
    }

    /**
     * Creates and persists a RegisteredClient.
     *
     * @param clientId the client ID
     * @param redirectUris the redirect URIs that the client is allowed to use
     * @param scopes the scopes the client can request from the authorization server
     *
     * @return the generated client secret
     */
    public String create(
            String clientId, String clientName, List<String> redirectUris, List<String> scopes,
            List<AuthorizationGrantType> authorizationGrantTypes
    ) {

        String clientSecret = generateClientSecret(64);

        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(clientId)
                .clientName(clientName)
                .clientSecret(clientSecret)
                .redirectUris(set -> set.addAll(redirectUris))
                .authorizationGrantTypes(set -> set.addAll(authorizationGrantTypes))
                .scopes(set -> set.addAll(scopes))
                .build();

        registeredClientRepository.save(registeredClient);

        return clientSecret;
    }

    /**
     *
     * Deletes the RegisteredClient from the server
     *
     * @param id the RegisteredClient id
     * @throws ClientNotFoundException if the RegisteredClient does not exist
     */
    public void delete(UUID id) {

        RegisteredClient registeredClient = this.registeredClientRepository.findById(id.toString());

        if (registeredClient == null)
            throw new ClientNotFoundException(id);

        registeredClientRepository.delete(registeredClient);
    }

    private String generateClientSecret(int byteLength) {

        SecureRandom secureRandom = new SecureRandom();

        byte[] bytes = new byte[byteLength];
        secureRandom.nextBytes(bytes);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

}
