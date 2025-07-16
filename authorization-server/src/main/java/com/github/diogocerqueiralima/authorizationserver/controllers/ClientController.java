package com.github.diogocerqueiralima.authorizationserver.controllers;

import com.github.diogocerqueiralima.authorizationserver.dto.ClientDto;
import com.github.diogocerqueiralima.authorizationserver.dto.CreateClientDto;
import com.github.diogocerqueiralima.authorizationserver.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     *
     * Handles GET requests to retrieve the list of clients and render the client's page.
     *
     * @param model the model used to pass data to the view
     * @return the name of the template to render ("clients")
     */
    @GetMapping
    public String getClients(Model model) {

        model.addAttribute(
                "clients",
                clientService.getAll().stream()
                        .map(client ->
                                        new ClientDto(
                                                UUID.fromString(client.getId()),
                                                client.getClientId(),
                                                client.getClientName(),
                                                client.getScopes(),
                                                client.getAuthorizationGrantTypes().stream()
                                                        .map(AuthorizationGrantType::getValue)
                                                        .collect(Collectors.toSet())
                                        )
                        )
                        .collect(Collectors.toSet())
        );

        return "clients";
    }

    /**
     *
     * POST endpoint to create a new OAuth2 client
     *
     * @param dto the data used to create the client
     * @return redirect to the client's page
     */
    @PostMapping
    public String create(@ModelAttribute @Valid CreateClientDto dto, RedirectAttributes redirectAttributes) {

        String clientSecret = clientService.create(
                dto.clientId(), dto.clientName(), dto.redirectUris(), dto.scopes(), dto.authorizationGrantTypes()
        );

        redirectAttributes.addFlashAttribute("clientSecret", clientSecret);

        return "redirect:/admin/clients";
    }

    /**
     *
     * DELETE endpoint to delete the OAuth2 client
     *
     * @param id the RegisteredClient id to delete
     * @return redirect to the client's page
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable UUID id) {

        clientService.delete(id);

        return "redirect:/admin/clients";
    }

}
