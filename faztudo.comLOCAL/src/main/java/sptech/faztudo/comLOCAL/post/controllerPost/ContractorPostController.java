package sptech.faztudo.comLOCAL.post.controllerPost;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.faztudo.comLOCAL.post.domainPost.upload.ContractorPost;
import sptech.faztudo.comLOCAL.post.domainPost.upload.Image;
import sptech.faztudo.comLOCAL.post.repositoryPost.ContractorPostRepository;
import sptech.faztudo.comLOCAL.post.repositoryPost.ImageRepository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/contractor-post")
public class ContractorPostController {

    @Autowired
    private ContractorPostRepository contractorPostRepository;
    @Autowired
    private ImageRepository imageRepository;

    @PostMapping("/")
    @Operation(summary = "Post Contratante", description = "Postagem para contratante, envia descrição do trabalho e imagem.", tags = "USER - CONTRACTOR - POST")
    public ResponseEntity<ContractorPost> criarContractorPost(@RequestBody ContractorPost contractorPost) {
        contractorPost.setDataCriacao(LocalDateTime.now()); // Defina a data de criação
        ContractorPost novoContractorPost = contractorPostRepository.save(contractorPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoContractorPost);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Post Contratante", description = "Recupera as postagens através do id do usuário.", tags = "USER - CONTRACTOR - POST")
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
    @Operation(summary = "Post Contratante", description = "Atualiza as postagens através do id do usuário.", tags = "USER - CONTRACTOR - POST")
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
    @Operation(summary = "Post Contratante", description = "Deleta postagens através do id do usuário.", tags = "USER - CONTRACTOR")
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
