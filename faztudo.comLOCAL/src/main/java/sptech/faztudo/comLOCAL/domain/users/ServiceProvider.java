package sptech.faztudo.comLOCAL.domain.users;

import sptech.faztudo.comLOCAL.domain.users.User;

public class ServiceProvider extends User {

    private int category;

    public ServiceProvider(int id,
                           String name,
                           String lastName,
                           String cpf,
                           String state,
                           String city,
                           String phone,
                           String email,
                           String senha,
                           int category) {
        super(id, name, lastName, cpf, state, city, phone, email, senha);
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }


}
