package sptech.faztudo.comLOCAL;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.lang.NonNull;

@Entity
public class User {


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public User() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

