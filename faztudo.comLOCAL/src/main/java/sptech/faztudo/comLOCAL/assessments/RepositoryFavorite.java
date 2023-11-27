package sptech.faztudo.comLOCAL.assessments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sptech.faztudo.comLOCAL.users.domain.users.User;

import java.util.List;

public interface RepositoryFavorite extends JpaRepository<Favorite, Long> {

    @Query("""
            select f.id from Favorite f where f.fkContractor = ?1 and f.fkProvider = ?2       
                    """)
    long existsByFk(int fk_contractor, int fk_provider);

    @Query("""
            select u from User u
            where u.id in (select f.id from User u join Favorite f on f.fkContractor = u.id)
            """)
    List<User> findByFavorite();
}
