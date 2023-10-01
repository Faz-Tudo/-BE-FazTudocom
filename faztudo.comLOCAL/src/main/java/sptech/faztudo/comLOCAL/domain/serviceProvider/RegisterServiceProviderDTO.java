package sptech.faztudo.comLOCAL.domain.serviceProvider;

public record RegisterServiceProviderDTO(String name,
                                         String lastName,
                                         String cpf,
                                         String state,
                                         String city,
                                         String phone,
                                         String email,
                                         String senha,
                                         int category) {
}
