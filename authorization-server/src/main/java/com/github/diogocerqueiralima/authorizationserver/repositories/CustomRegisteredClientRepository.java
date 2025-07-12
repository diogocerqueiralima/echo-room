package com.github.diogocerqueiralima.authorizationserver.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.List;

public class CustomRegisteredClientRepository implements RegisteredClientRepository {

    private static final String COLUMN_NAMES = "id, "
            + "client_id, "
            + "client_id_issued_at, "
            + "client_secret, "
            + "client_secret_expires_at, "
            + "client_name, "
            + "client_authentication_methods, "
            + "authorization_grant_types, "
            + "redirect_uris, "
            + "post_logout_redirect_uris, "
            + "scopes, "
            + "client_settings,"
            + "token_settings";

    private static final String TABLE_NAME = "oauth2_registered_client";

    private static final String LOAD_REGISTERED_CLIENT_SQL = "SELECT " + COLUMN_NAMES + " FROM " + TABLE_NAME;

    private final JdbcTemplate jdbcTemplate;
    private final RegisteredClientRepository registeredClientRepository;
    private final JdbcRegisteredClientRepository.RegisteredClientRowMapper mapper = new JdbcRegisteredClientRepository.RegisteredClientRowMapper();

    public CustomRegisteredClientRepository(
            JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.registeredClientRepository = registeredClientRepository;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        registeredClientRepository.save(registeredClient);
    }

    @Override
    public RegisteredClient findById(String id) {
        return registeredClientRepository.findById(id);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return registeredClientRepository.findByClientId(clientId);
    }

    public List<RegisteredClient> findAll() {
        return jdbcTemplate.query(LOAD_REGISTERED_CLIENT_SQL, mapper);
    }

}
