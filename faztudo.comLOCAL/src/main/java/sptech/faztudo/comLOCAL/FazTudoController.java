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
    public User register(@RequestBody User user){
        return UserRepository.save(user);
    }

    @GetMapping("/")
    public List<User> viewAll(){
        return UserRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable int id){
        return UserRepository.findById(id);
    }

    @PutMapping("/")
    public User update(@RequestBody User user){
        return UserRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        User user = findById(id);
        UserRepository.delete(user);
    }

}
