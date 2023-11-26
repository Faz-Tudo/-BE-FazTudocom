package sptech.faztudo.comLOCAL.users.domain.serviceProvider;

import java.time.LocalDate;

public record RegisterServiceProviderDTO(String name,
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
                                         String descricao,
                                         int category) {
}
