package sptech.faztudo.comLOCAL.post.controllerPost;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sptech.faztudo.comLOCAL.post.domainPost.upload.ContractorPost;
import sptech.faztudo.comLOCAL.post.repositoryPost.ContractorPostRepository;
import sptech.faztudo.comLOCAL.post.repositoryPost.ImageRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class ContractorPostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ContractorPostController contractorPostController;

    @MockBean
    private ContractorPostRepository contractorPostRepository;

    @Test
    void criarContractorPost() {

        ContractorPost contractorPost = new ContractorPost();

        var dateTime = LocalDateTime.now();
        contractorPost.setDataCriacao(dateTime);

        when(contractorPostRepository.save(contractorPost)).thenReturn(contractorPost);

       var response = contractorPostController.criarContractorPost(contractorPost);
        verify(contractorPostRepository, times(1)).save(any(ContractorPost.class));

        assertEquals(201, response.getStatusCode().value());
        assertEquals(contractorPost, response.getBody());

    }

    @Test
    void obterContractorPost() {

        ContractorPost contractorPost = new ContractorPost();
        var id = 5;

        var teste = when(contractorPostRepository.findById((long) id)).thenReturn(Optional.of(contractorPost));
         teste.

        if () {

            return ResponseEntity.ok(contractorPost);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Test
    void atualizarContractorPost() {

        // Crie um objeto do seu controlador
        ContractorPostController contractorPostController = new ContractorPostController();

        // Crie mocks para suas dependências (substitua pelos seus repositórios reais)
        ContractorPostRepository contractorPostRepository = mock(ContractorPostRepository.class);
        ImageRepository imageRepository = mock(ImageRepository.class);


        // Crie um objeto de atualização simulado
        Map<String, Object> updates = new HashMap<>();
        updates.put("descricao", "Nova descrição");

        // Crie um objeto ContractorPost simulado
        ContractorPost contractorPostExistente = new ContractorPost();
        Long idExistente = 1L;
        when(contractorPostRepository.findById(idExistente)).thenReturn(Optional.of(contractorPostExistente));

        // Execute o método que você deseja testar
        ResponseEntity<ContractorPost> resposta = contractorPostController.atualizarContractorPost(idExistente, updates);

        // Verifique o resultado
        assertEquals(200, resposta.getStatusCodeValue()); // ou o código de status esperado

        // Verifique se o método save foi chamado no repositório
        verify(contractorPostRepository, times(1)).save(contractorPostExistente);

        // Verifique se as propriedades foram atualizadas corretamente
        assertEquals("Nova descrição", contractorPostExistente.getDescricao());
    }
}

