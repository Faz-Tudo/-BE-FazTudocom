package sptech.faztudo.comLOCAL.controllers;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.faztudo.comLOCAL.domain.users.AuthenticationDTO;
import sptech.faztudo.comLOCAL.domain.users.LoginResponseDTO;
import sptech.faztudo.comLOCAL.domain.users.RegisterDTO;
import sptech.faztudo.comLOCAL.domain.users.User;
import sptech.faztudo.comLOCAL.infra.security.TokeService;
import sptech.faztudo.comLOCAL.repositorys.userRepository;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private userRepository repository;

    @Autowired
    private TokeService tokeService;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){

        var userNamePassWord = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(userNamePassWord);

        var token = tokeService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterDTO data){


        if(!isUserValid(data) && this.repository.findByEmail(data.email()) != null) return ResponseEntity.status(400).build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        User newUser = new User(data.name(), data.lastName(), data.cpf(), data.state(),
                data.city(), data.phone(), data.email(), encryptedPassword, data.role());


        this.repository.save(newUser);
        return ResponseEntity.status(201).body(newUser);
    }





    private boolean isUserValid(RegisterDTO data) {
        return isValidName(data.name()) &&
                isValidLastName(data.lastName()) &&
                isValidCPF(data.cpf()) &&
                isValidEmail(data.email()) &&
                isValidPhone(data.phone()) &&
                isValidPassword(data.senha());
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

