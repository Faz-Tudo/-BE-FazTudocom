package sptech.faztudo.comLOCAL.controllers;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sptech.faztudo.comLOCAL.csv.GerenciadorDeArquivo;
import sptech.faztudo.comLOCAL.csv.ListaObj;
import sptech.faztudo.comLOCAL.domain.users.User;
import sptech.faztudo.comLOCAL.repositorys.userRepository;


import java.util.List;

@RestController
@RequestMapping("/users")
public class FazTudoController {

    @Autowired
    public userRepository UserRepository;



    @GetMapping("/")
    public ResponseEntity<List<User>> findAll(){

        List<User> users  = UserRepository.findAll();

        GerenciadorDeArquivo.gravaArquivoCsv(users,"ArquivoCSV");

        return ResponseEntity.status(200).body(users);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable int id){
        User user =  UserRepository.findById(id);
        return ResponseEntity.status(200).body(user);
    }

    @PutMapping("/")
    public User update(@RequestBody User user){
        return UserRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable int id){

        try {
            User user = UserRepository.findById(id);
            UserRepository.delete(user);
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).build();
    }




}
