package sptech.faztudo.comLOCAL.users.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.faztudo.comLOCAL.users.domain.users.User;


import java.util.List;

@RestController
@RequestMapping("/users")
public class FazTudoController {

    @Autowired
    public sptech.faztudo.comLOCAL.users.repositorys.UserRepository UserRepository;

    @GetMapping("/")
    @Operation(summary = "Get All Users", description = "Listar todos os usuários.", tags = "BACKOFFICE")
    public ResponseEntity<List<User>> findAll() {

        List<User> users = UserRepository.findAll();

        return ResponseEntity.status(200).body(users);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get User by Id", description = "Listar usuário por id.", tags = "BACKOFFICE")
    public ResponseEntity<User> findById(@PathVariable int id) {
        User user = UserRepository.findById(id);
        return ResponseEntity.status(200).body(user);
    }

    @PutMapping("/")
    @Operation(summary = "Update User", description = "Atualizar usuário.", tags = "BACKOFFICE")
    public User update(@RequestBody User user) {
        return UserRepository.save(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete User by Id", description = "Deleta usuário por id.", tags = "BACKOFFICE")
    public ResponseEntity<User> delete(@PathVariable int id) {

        try {
            User user = UserRepository.findById(id);
            UserRepository.delete(user);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).build();
    }
//    @PostMapping("/favorite/{id}")
//    @Operation(summary = "Add Favorite by Id", description = "Adiciona um favorito por id", tags = "BACKOFFICE")
//    public ResponseEntity<?> addFavorite (@PathVariable int id){
//        User userQueFavorita = new User();
//        try {
//            User user = UserRepository.findById(id);
//            userQueFavorita.addFavorito(id);
//        } catch (Exception e) {
//            System.out.println(e);
//            return ResponseEntity.status(404).build();
//        }
//        return ResponseEntity.status(200).build();
//    }
//    @DeleteMapping("/favorite/{id}")
//    @Operation(summary = "Delete Favorite by Id", description = "Deleta um favorito por id", tags = "BACKOFFICE")
//    public ResponseEntity<?> deleteFavorite (@PathVariable int id){
//        User userQueFavorita = new User();
//        try {
//            User user = UserRepository.findById(id);
//            userQueFavorita.removeFavorito(id);
//        } catch (Exception e) {
//            System.out.println(e);
//            return ResponseEntity.status(404).build();
//        }
//        return ResponseEntity.status(200).build();
//    }


}
