package sptech.faztudo.comLOCAL.users.domain.contractor;

import jakarta.persistence.Entity;
import sptech.faztudo.comLOCAL.users.domain.users.User;

@Entity
public class Contractor extends User {

    private boolean proUser;

    public Contractor(String name,
                      String lastName,
                      String cpf,
                      String state,
                      String city,
                      String phone,
                      String email,
                      String senha,
                      boolean proUser) {
        super(name, lastName, cpf, state, city, phone, email, senha);
        this.proUser = proUser;
    }

    public Contractor(){

    }

    public boolean isProUser() {
        return proUser;
    }

    public void setProUser(boolean proUser) {
        this.proUser = proUser;
    }


}

