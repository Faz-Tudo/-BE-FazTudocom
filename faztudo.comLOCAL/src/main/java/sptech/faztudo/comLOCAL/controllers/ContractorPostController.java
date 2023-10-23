package sptech.faztudo.comLOCAL.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.faztudo.comLOCAL.domain.upload.ContractorPost;
import sptech.faztudo.comLOCAL.domain.upload.Image;
import sptech.faztudo.comLOCAL.repositorys.contractorPostRepository;
import sptech.faztudo.comLOCAL.repositorys.imageRepository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/contractor-post")
public class ContractorPostController {

    @Autowired
    private contractorPostRepository contractorPostRepository;
    @Autowired
    private imageRepository imageRepository;

    @PostMapping("/")
    public ResponseEntity<ContractorPost> criarContractorPost(@RequestBody ContractorPost contractorPost) {
        contractorPost.setDataCriacao(LocalDateTime.now()); // Defina a data de criação
        ContractorPost novoContractorPost = contractorPostRepository.save(contractorPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoContractorPost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractorPost> obterContractorPost(@PathVariable Long id) {
        Optional<ContractorPost> contractorPostOptional = contractorPostRepository.findById(id);
        if (contractorPostOptional.isPresent()) {
            ContractorPost contractorPost = contractorPostOptional.get();
            return ResponseEntity.ok(contractorPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ContractorPost> atualizarContractorPost(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<ContractorPost> contractorPostOptional = contractorPostRepository.findById(id);
        if (contractorPostOptional.isPresent()) {
            ContractorPost contractorPost = contractorPostOptional.get();

            if (updates.containsKey("descricao")) {
                contractorPost.setDescricao((String) updates.get("descricao"));
            }

            if (updates.containsKey("categoria")) {
                contractorPost.setCategoria((String) updates.get("categoria"));
            }

            if (updates.containsKey("foto")) {
                String fotoValue = (String) updates.get("foto");

                Long fotoId = Long.parseLong(fotoValue);

                Image foto = imageRepository.findById(fotoId).orElse(null);

                if (foto != null) {
                    contractorPost.setFoto(foto);

                } else {
                    return ResponseEntity.status(404).build();
                }
            }

            ContractorPost contractorPostAtualizado = contractorPostRepository.save(contractorPost);
            return ResponseEntity.ok(contractorPostAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirContractorPost(@PathVariable Long id) {
        Optional<ContractorPost> contractorPostOptional = contractorPostRepository.findById(id);
        if (contractorPostOptional.isPresent()) {
            ContractorPost contractorPost = contractorPostOptional.get();
            contractorPostRepository.delete(contractorPost);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
