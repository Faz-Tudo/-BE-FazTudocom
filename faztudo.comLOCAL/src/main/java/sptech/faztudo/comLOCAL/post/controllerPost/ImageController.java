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
import sptech.faztudo.comLOCAL.post.repositoryPost.ImageRepository;
import sptech.faztudo.comLOCAL.users.domain.users.User;
import sptech.faztudo.comLOCAL.users.repositorys.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/upload/{tipo}/{user}")
    @Operation(summary = "Imagem Contratante", description = "Envio de imagens para uso do contratante.", tags = "USER - CONTRACTOR")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Integer tipo, @PathVariable Integer user) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("O arquivo está vazio.");
            }
            Optional<User> userOptional = userRepository.findById(user);

            Image image = new Image();
            image.setName(file.getOriginalFilename());
            image.setData(file.getBytes());
            image.setTipo(tipo);
            image.setFkUser(userOptional);
            imageRepository.save(image);

            return ResponseEntity.ok("Imagem enviada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao enviar a imagem: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Imagem Contratante", description = "recupera imagens enviadas através do contrante através de id.", tags = "USER - CONTRACTOR")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<Image> imageOptional = imageRepository.findById(id);

        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
