package sptech.faztudo.comLOCAL.assessments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositoryFavorite extends JpaRepository<Favorite, Long> {

    @Query("""
            select f.id from Favorite f where f.fkContractor = ?1 and f.fkProvider = ?2       
                    """)
    List<Long> existsByFk(int fk_contractor, int fk_provider);
}
