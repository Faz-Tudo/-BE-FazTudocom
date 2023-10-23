package sptech.faztudo.comLOCAL.users.domain.users;

import sptech.faztudo.comLOCAL.users.UserRole;

public record RegisterDTO(String name,
                          String lastName,
                          String cpf,
                          String state,
                          String city,
                          String phone,
                          String email,
                          String senha,
                          UserRole role) {
}
