package sptech.faztudo.comLOCAL.post.controllerPost;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sptech.faztudo.comLOCAL.post.domainPost.upload.Image;
import sptech.faztudo.comLOCAL.post.domainPost.upload.ImageDTO;
import sptech.faztudo.comLOCAL.post.repositoryPost.ImageRepository;
import sptech.faztudo.comLOCAL.users.domain.users.User;
import sptech.faztudo.comLOCAL.users.repositorys.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/upload/{tipo}/{user}")
    @Operation(summary = "Imagens Geral", description = "Envio de imagens, uso geral, distinção por ID de usuario e TIPO de imagen", tags = "IMAGES")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Integer tipo, @PathVariable Integer user) throws IOException {

        Optional<User> optionalUser = userRepository.findById(user);


        if (optionalUser.isPresent()) {
            Image image = new Image();
            image.setName(file.getOriginalFilename());
            System.out.println(file.getOriginalFilename());
            image.setData(file.getBytes());
            image.setTipo(tipo);
            image.setFkUser(user);

            imageRepository.save(image);

            return ResponseEntity.status(201).body("Upload da imagem realizado com sucesso!");
        } else {
            return ResponseEntity.status(404).body("Usuario não encontrado!");
        }

    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Imagem Contratante", description = "Recupera informações de imagens enviadas pelo contratante através do ID.", tags = "USER - CONTRACTOR")
    public ResponseEntity<List<ImageDTO>> getImages(@PathVariable Long id) {

        List<Image> imagens = imageRepository.findAllByFkUser(id);

        if (imagens.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        List<ImageDTO> imagensDTO = new ArrayList<>();

        for (Image imagem : imagens) {

            String base64Data = Base64.getEncoder().encodeToString(imagem.getData());
            String nome = imagem.getName();
            Integer imagemTipo = imagem.getTipo();
            Integer fkUser = imagem.getFkUser();

            ImageDTO imagemDTO = new ImageDTO(base64Data, nome, imagemTipo, fkUser);
            imagensDTO.add(imagemDTO);
        }

        return new ResponseEntity<>(imagensDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) {

        if (imageRepository.existsById(id)) {

            imageRepository.deleteById(id);
            return ResponseEntity.ok("Imagem removida com sucesso.");
        } else {

            return ResponseEntity.notFound().build();
        }
    }


}
