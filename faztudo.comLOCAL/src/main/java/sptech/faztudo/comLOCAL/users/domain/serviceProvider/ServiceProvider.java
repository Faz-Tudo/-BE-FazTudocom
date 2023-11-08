package sptech.faztudo.comLOCAL.users.domain.serviceProvider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import sptech.faztudo.comLOCAL.users.domain.users.User;

import java.time.LocalDate;

@Entity
public class ServiceProvider extends User {

    @Column(name = "category")
    private int category;

    public ServiceProvider(String name,
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
                           int category) {
        super( name, lastName, cpf, dt_nascimento, cep, logradouro, state, city, phone, email, senha);
        this.category = category;
    }
    public ServiceProvider() {

    }



    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }


}
