package sptech.faztudo.comLOCAL.users.domain.contractor;

public record RegisterContractorDTO(String name,
                                    String lastName,
                                    String cpf,
                                    String state,
                                    String city,
                                    String phone,
                                    String email,
                                    String senha,
                                    boolean proUser) {
}