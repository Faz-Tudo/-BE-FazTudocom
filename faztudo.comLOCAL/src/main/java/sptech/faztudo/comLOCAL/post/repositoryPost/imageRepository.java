package sptech.faztudo.comLOCAL.post.repositoryPost;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.faztudo.comLOCAL.post.domainPost.upload.Image;

public interface imageRepository extends JpaRepository<Image, Long>  {
}