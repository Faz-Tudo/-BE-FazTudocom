package sptech.faztudo.comLOCAL.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.faztudo.comLOCAL.domain.upload.Image;

public interface imageRepository extends JpaRepository<Image, Long>  {
}
