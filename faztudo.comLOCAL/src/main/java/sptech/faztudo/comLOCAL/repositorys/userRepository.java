package sptech.faztudo.comLOCAL.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import sptech.faztudo.comLOCAL.domain.users.User;

import java.util.List;

@Repository
public interface userRepository extends JpaRepository<User, Integer> {

    List<User> findAll();
    User findById(int id);
    UserDetails findByEmail(String email);
}

