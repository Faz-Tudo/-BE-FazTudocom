package sptech.faztudo.comLOCAL.users.domain.contractor;

import java.time.LocalDate;

public record RegisterContractorDTO(String name,
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
                                    boolean proUser) {
}
