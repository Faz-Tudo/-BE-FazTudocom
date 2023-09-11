package sptech.faztudo.comLOCAL.domain.users;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sptech.faztudo.comLOCAL.UserRole;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements UserDetails {


    @Id
    @Column(unique = true, name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    @Size(min = 5, max = 50, message = "Nome com tamanho de inválido")
    @NotBlank(message = "Preencha o campo nome corretamente. ")
    private String name;

    @Column(name = "lastName", nullable = false)
    @Size(min = 5, max = 150, message = "Sobrenome com tamanho inválido")
    @NotBlank(message = "Preencha o campo sobrenome corretamente. ")
    private String lastName;

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    @CPF(message = "CPF inválido!")
    @NotNull
    private String cpf;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "phone", unique = true, nullable = false)
//    @Pattern(regexp = "\\([0-9]{2}\\) [0-9]{4,5}-[0-9]{4}", message = "Telefone inválido")
    @NotNull
    private String phone;

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "Email inválido!")
    @NotNull
    private String email;

    @Column(name = "senha", nullable = false)
    @Pattern.List({
            @Pattern(regexp = ".*[A-Z].*", message = "A senha deve conter pelo menos uma letra maiúscula"),
            @Pattern(regexp = ".*[a-z].*", message = "A senha deve conter pelo menos uma letra minúscula"),
            @Pattern(regexp = ".*\\d.*", message = "A senha deve conter pelo menos um dígito"),
            @Pattern(regexp = "(?=.*[!@#$%^&*()]).{8,}$", message = "A senha deve conter pelo menos um caractere especial e 8 caracteres.")
    })
    @NotNull
    private String senha;

    @Column(name = "role")
    private UserRole role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User() {

    }


    public User(int id, String name, String lastName, String cpf, String state, String city, String phone, String email, String senha) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
        this.state = state;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.senha = senha;

    }

    public User(String name, String lastName, String cpf, String state, String city, String phone,
                String email, String senha, UserRole role) {
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
        this.state = state;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

