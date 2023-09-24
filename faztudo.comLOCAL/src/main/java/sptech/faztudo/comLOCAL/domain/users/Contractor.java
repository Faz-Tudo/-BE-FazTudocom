package sptech.faztudo.comLOCAL.domain.users;

import sptech.faztudo.comLOCAL.domain.users.User;

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

    public boolean isProUser() {
        return proUser;
    }

    public void setProUser(boolean proUser) {
        this.proUser = proUser;
    }


}

