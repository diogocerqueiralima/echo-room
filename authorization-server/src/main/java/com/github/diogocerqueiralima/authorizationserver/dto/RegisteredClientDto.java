package com.github.diogocerqueiralima.authorizationserver.dto;

import java.util.Set;

public record RegisteredClientDto(
        String clientId, String clientName, Set<String> scopes, Set<String> authorizationGrantTypes
) {}
