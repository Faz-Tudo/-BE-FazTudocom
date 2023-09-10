package sptech.faztudo.comLOCAL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class FazTudoController {

    @Autowired
    public userRepository UserRepository;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        if (user.getName().isEmpty() || user.getCpf().isEmpty() || user.getEmail().isEmpty() ||
                user.getPhone().isEmpty() || user.getSenha().isEmpty()||user.getSenha().length() < 8||
                user.getName().length() < 5 || user.getName().length() > 50 || user.getLastName().length() < 5||
                user.getLastName().length() > 150 || user.getCpf().length() < 11|| user.getPhone().length() < 8){
            return ResponseEntity.status(400).build();
        }


        UserRepository.save(user);
        return ResponseEntity.status(201).body(user);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findAll(){

        List<User> users  = UserRepository.findAll();
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
