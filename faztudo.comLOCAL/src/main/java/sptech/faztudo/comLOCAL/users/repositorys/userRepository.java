package sptech.faztudo.comLOCAL.users.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import sptech.faztudo.comLOCAL.users.domain.users.User;

import java.util.List;

@Repository
public interface userRepository extends JpaRepository<User, Integer> {

    List<User> findAll();
    User findById(Long id);
    UserDetails findByEmail(String email);

//    @Transactional
//    @Modifying
//    @Query("UPDATE user u " +
//            "SET u.enabled = TRUE WHERE u.email = ?1")
//    int enableAppUser(String email);
}

