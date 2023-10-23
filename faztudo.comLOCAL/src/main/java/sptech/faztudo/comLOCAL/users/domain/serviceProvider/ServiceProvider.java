package sptech.faztudo.comLOCAL.users.domain.serviceProvider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import sptech.faztudo.comLOCAL.users.domain.users.User;

@Entity
public class ServiceProvider extends User {

    @Column(name = "category")
    private int category;

    public ServiceProvider(String name,
                           String lastName,
                           String cpf,
                           String state,
                           String city,
                           String phone,
                           String email,
                           String senha,
                           int category) {
        super( name, lastName, cpf, state, city, phone, email, senha);
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
