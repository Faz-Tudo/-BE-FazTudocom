package sptech.faztudo.comLOCAL.domain.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import sptech.faztudo.comLOCAL.domain.users.User;

@Entity
public class ServiceProvider extends User {

    @Column(name = "category", nullable = false)
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
