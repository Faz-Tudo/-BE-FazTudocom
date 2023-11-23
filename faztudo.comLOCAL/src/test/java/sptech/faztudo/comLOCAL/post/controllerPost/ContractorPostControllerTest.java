package sptech.faztudo.comLOCAL.post.controllerPost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import sptech.faztudo.comLOCAL.post.domainPost.upload.ContractorPost;
import sptech.faztudo.comLOCAL.post.repositoryPost.ContractorPostRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
class ContractorPostControllerTest {

    @Mock
    private ContractorPostRepository contractorPostRepository;
    @InjectMocks
    ContractorPostController contractorPostController;


    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

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



    }

    @Test
    void atualizarContractorPost() {


    }
}

