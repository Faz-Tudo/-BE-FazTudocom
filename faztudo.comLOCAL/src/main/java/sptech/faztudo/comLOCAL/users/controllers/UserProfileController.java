package sptech.faztudo.comLOCAL.users.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sptech.faztudo.comLOCAL.users.domain.contractor.Contractor;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.ServiceProvider;
import sptech.faztudo.comLOCAL.users.domain.users.UpdateDescriptionDTO;
import sptech.faztudo.comLOCAL.users.domain.users.UpdateUserPasswordDTO;
import sptech.faztudo.comLOCAL.users.domain.users.User;
import sptech.faztudo.comLOCAL.users.repositorys.UserRepository;

@Controller
@RequestMapping("profile")
public class UserProfileController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;


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
    @Operation(summary = "Update Password", description = "Atualiza senha para todos os usuários.", tags = "PROFILE")
    public ResponseEntity<User> updatePassword(
            @RequestBody UpdateUserPasswordDTO partialUpdate,
            @PathVariable int id
    ) {
        User newPassword =  repository.findById(id);
        String senha = passwordEncoder.encode(partialUpdate.senha());
        newPassword.setSenha(senha);

        return ResponseEntity.status(200).body(repository.save(newPassword));

    }

    @PatchMapping("/update-description/{id}")
    @Operation(summary = "Update Description", description = "Atualiza a descrição de perfil para todos os usuários.", tags = "PROFILE")
    public ResponseEntity<User> updateDescription(
            @RequestBody UpdateDescriptionDTO partialUpdate,
            @PathVariable int id
    ) {
        User newPassword =  repository.findById(id);
        newPassword.setDescricao(partialUpdate.descricao());

        return ResponseEntity.status(200).body(repository.save(newPassword));

    }
}
