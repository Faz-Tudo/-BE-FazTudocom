package sptech.faztudo.comLOCAL.users.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.faztudo.comLOCAL.post.domainPost.upload.ContractorPost;
import sptech.faztudo.comLOCAL.post.repositoryPost.ContractorPostRepository;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.ServiceFila;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.ServicePilha;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.ServiceProvider;
import sptech.faztudo.comLOCAL.users.domain.users.User;
import sptech.faztudo.comLOCAL.users.repositorys.userRepository;

import java.util.Optional;

@RestController("/provider")
public class ProviderController {

    @Autowired
    private userRepository repository;

    @Autowired
    private ServiceProvider serviceProvider;
    @Autowired
    private ContractorPostRepository contractorPostRepository;
    @Autowired
    private ServicePilha<ContractorPost> servicePilha;
    @Autowired
    private ServiceFila<ContractorPost> serviceFila;


    @PutMapping("/{id}")
    @Operation(summary = "Alterar FK", description = "alterar FK das postagens através do id do usuário.", tags = "USER - PROVIDER - POST")
    public ResponseEntity servico(@PathVariable Long id, @PathVariable Long idUser){
        Optional<ContractorPost> contractorPostOptional = contractorPostRepository.findById(id);
        if (repository.existsById(Math.toIntExact(id))) {
            contractorPostOptional.get().setFkProvider(repository.findById(idUser));
            servicePilha.push(contractorPostOptional.get());
            return ResponseEntity.ok(repository.findById(idUser));
        }

        return ResponseEntity.notFound().build();
    }


    @GetMapping("/user/{id}")
    @Operation(summary = "Obter Usuarios", description = "Recupera as postagens através do id do usuário.", tags = "USER - CONTRACTOR - POST")
    public ResponseEntity obterContractorPost(@PathVariable Long id) {
        Optional<ContractorPost> contractorPostOptional = contractorPostRepository.findById(id);
        if (contractorPostOptional.isPresent()) {
            ContractorPost contractorPost = contractorPostOptional.get();
            serviceFila.insert(servicePilha.peek());
            servicePilha.pop();
            return ResponseEntity.ok(contractorPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
