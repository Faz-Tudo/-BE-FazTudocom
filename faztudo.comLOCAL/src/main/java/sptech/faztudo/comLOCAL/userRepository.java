package sptech.faztudo.comLOCAL;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userRepository extends CrudRepository<User, Integer> {

    List<User> findAll();

    User findById(int id);
}

