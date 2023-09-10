package sptech.faztudo.comLOCAL;

import io.micrometer.common.util.StringUtils;
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
        if (!isUserValid(user)){
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


    private boolean isUserValid(User user) {
        return isValidName(user.getName()) &&
                isValidLastName(user.getLastName()) &&
                isValidCPF(user.getCpf()) &&
                isValidEmail(user.getEmail()) &&
                isValidPhone(user.getPhone()) &&
                isValidPassword(user.getSenha());
    }

    private boolean isValidName(String name) {
        return !StringUtils.isEmpty(name) && name.length() >= 5 && name.length() <= 50;
    }

    private boolean isValidLastName(String lastName) {
        return !StringUtils.isEmpty(lastName) && lastName.length() >= 5 && lastName.length() <= 150;
    }

    private boolean isValidCPF(String cpf) {
        return !StringUtils.isEmpty(cpf) && cpf.length() == 11;
    }

    private boolean isValidEmail(String email) {
        return !StringUtils.isEmpty(email) ;
    }

    private boolean isValidPhone(String phone) {
        return !StringUtils.isEmpty(phone) && phone.length() >= 8;
    }

    private boolean isValidPassword(String password) {
        return !StringUtils.isEmpty(password) && password.length() >= 8;
    }

}
