package sptech.faztudo.comLOCAL.controllers;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sptech.faztudo.comLOCAL.domain.users.*;
import sptech.faztudo.comLOCAL.infra.security.TokeService;
import sptech.faztudo.comLOCAL.infra.security.tokenForRegister.ConfirmationToken;
import sptech.faztudo.comLOCAL.infra.security.tokenForRegister.ConfirmationTokenService;
import sptech.faztudo.comLOCAL.repositorys.userRepository;
import sptech.faztudo.comLOCAL.repositorys.serviceProviderRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private userRepository repository;

    @Autowired
    private serviceProviderRepository serviceProviderRepository;

    @Autowired
    private TokeService tokeService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;





    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){

        var userNamePassWord = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(userNamePassWord);

        var token = tokeService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data, UriComponentsBuilder uriComponentsBuilder){


        if(!isUserValid(data) && this.repository.findByEmail(data.email()) != null) return ResponseEntity.status(400).build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        User newUser = new User(data.name(), data.lastName(), data.cpf(), data.state(),
                data.city(), data.phone(), data.email(), encryptedPassword, data.role());


        this.repository.save(newUser);

        var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(newUser.getId()).toUri();

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(120),
                newUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return ResponseEntity.created(uri).body(confirmationToken);
    }
    @PostMapping("/register-service-provider")
    public ResponseEntity registerServiceProvider(@RequestBody @Valid RegisterServiceProviderDTO dataServiceProvider, UriComponentsBuilder uriComponentsBuilder){

            if(!isServiceProviderValid(dataServiceProvider) && this.serviceProviderRepository.findByEmail(dataServiceProvider.email()) != null) return ResponseEntity.status(400).build();

            String encryptedPassword = new BCryptPasswordEncoder().encode(dataServiceProvider.senha());
            ServiceProvider newUser = new ServiceProvider(dataServiceProvider.name(), dataServiceProvider.lastName(), dataServiceProvider.cpf(), dataServiceProvider.state(),
                    dataServiceProvider.city(), dataServiceProvider.phone(), dataServiceProvider.email(), encryptedPassword, dataServiceProvider.category());

            var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(newUser.getId()).toUri();
            this.serviceProviderRepository.save(newUser);

            return ResponseEntity.created(uri).body(newUser);
    }





    private boolean isUserValid(RegisterDTO data) {
        return isValidName(data.name()) &&
                isValidLastName(data.lastName()) &&
                isValidCPF(data.cpf()) &&
                isValidEmail(data.email()) &&
                isValidPhone(data.phone()) &&
                isValidPassword(data.senha());
    }
   private boolean isServiceProviderValid(RegisterServiceProviderDTO dataServiceProvider) {
        return isValidName(dataServiceProvider.name()) &&
                isValidLastName(dataServiceProvider.lastName()) &&
                isValidCPF(dataServiceProvider.cpf()) &&
                isValidEmail(dataServiceProvider.email()) &&
                isValidPhone(dataServiceProvider.phone()) &&
                isValidPassword(dataServiceProvider.senha());
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

