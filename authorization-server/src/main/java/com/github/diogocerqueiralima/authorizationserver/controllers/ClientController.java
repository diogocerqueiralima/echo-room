package com.github.diogocerqueiralima.authorizationserver.controllers;

import com.github.diogocerqueiralima.authorizationserver.dto.CreateClientDto;
import com.github.diogocerqueiralima.authorizationserver.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String getClients(Model model) {

        model.addAttribute("clients", clientService.getAll());

        return "clients";
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
    public String create(@RequestBody @Valid CreateClientDto dto) {

        clientService.create(
                dto.clientId(), dto.clientSecret(), dto.redirectUris(), dto.scopes()
        );

        return "redirect:/admin/clients";
    }

}
