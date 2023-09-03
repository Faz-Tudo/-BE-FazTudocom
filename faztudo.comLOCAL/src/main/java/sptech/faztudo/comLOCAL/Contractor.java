package sptech.faztudo.comLOCAL;

public class Contractor extends User{

    private boolean proUser;

    public Contractor(int id, String name, String lastName, String cpf, String state, String city, String phone, String email, String senha, boolean proUser) {
        super(id, name, lastName, cpf, state, city, phone, email, senha);
        this.proUser = proUser;
    }

    public boolean isProUser() {
        return proUser;
    }

    public void setProUser(boolean proUser) {
        this.proUser = proUser;
    }


}

