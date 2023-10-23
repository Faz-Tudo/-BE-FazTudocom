package sptech.faztudo.comLOCAL.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.faztudo.comLOCAL.domain.users.User;

import java.util.List;

public interface csvRepository extends JpaRepository<User, Integer> {

    List<User> findAll();


}
