package sptech.faztudo.comLOCAL.users.domain.contractor;

import jakarta.persistence.Entity;
import sptech.faztudo.comLOCAL.users.UserRole;
import sptech.faztudo.comLOCAL.users.domain.users.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Contractor extends User {

    private boolean proUser;

    public Contractor(String name,
                      String lastName,
                      String cpf,
                      LocalDate dt_nascimento,
                      String cep,
                      String logradouro,
                      String state,
                      String city,
                      String phone,
                      String email,
                      String senha,
                      LocalDateTime dt_cadastro,
                      String descricao,
                      boolean proUser) {
        super(name, lastName, cpf, dt_nascimento, cep, logradouro, state, city, phone, email, senha,dt_cadastro,descricao);
        this.proUser = proUser;
    }

    public Contractor() {

    }


    public boolean isProUser() {
        return proUser;
    }

    public void setProUser(boolean proUser) {
        this.proUser = proUser;
    }


}

