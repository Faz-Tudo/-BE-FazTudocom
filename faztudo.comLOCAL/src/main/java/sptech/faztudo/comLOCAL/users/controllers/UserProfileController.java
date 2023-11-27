package sptech.faztudo.comLOCAL.users.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sptech.faztudo.comLOCAL.users.domain.contractor.Contractor;
import sptech.faztudo.comLOCAL.users.domain.contractor.RegisterContractorDTO;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.ServiceProvider;
import sptech.faztudo.comLOCAL.users.domain.users.AuthenticationDTO;
import sptech.faztudo.comLOCAL.users.domain.users.UpdateUserPassword;
import sptech.faztudo.comLOCAL.users.domain.users.User;
import sptech.faztudo.comLOCAL.users.repositorys.UserRepository;

@Controller
@RequestMapping("profile")
public class UserProfileController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PutMapping("/update-contractor/{id}")
    @Operation(summary = "Update", description = "Atualiza dados de contratantes.", tags = "PROFILE")
    public ResponseEntity<Contractor> update(
            @PathVariable int id,
            @RequestBody @Valid Contractor contractor
    ) {
        if (repository.existsById(id)){
            contractor.setId(id);
            return ResponseEntity.status(200).body(repository.save(contractor));
        }

        return ResponseEntity.status(404).build();
    }

    @PutMapping("/update-service-provider/{id}")
    @Operation(summary = "Update", description = "Atualiza dados de prestadores.", tags = "PROFILE")
    public ResponseEntity<ServiceProvider> update(
            @PathVariable int id,
            @RequestBody @Valid ServiceProvider serviceProvider
    ) {
        if (repository.existsById(id)){
            serviceProvider.setId(id);
            return ResponseEntity.status(200).body(repository.save(serviceProvider));
        }

        return ResponseEntity.status(404).build();
    }

    @PatchMapping("/update-password/{id}")
    public ResponseEntity<User> partialUpdateName(
            @RequestBody UpdateUserPassword partialUpdate,
            @PathVariable int id
    ) {
        User newPassword =  repository.findById(id);

        String senha = passwordEncoder.encode(partialUpdate.senha());

        newPassword.setSenha(senha);

        return ResponseEntity.status(200).body(repository.save(newPassword));

    }
}
