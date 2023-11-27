package sptech.faztudo.comLOCAL.assessments;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.faztudo.comLOCAL.post.repositoryPost.ContractorPostRepository;
import sptech.faztudo.comLOCAL.users.domain.contractor.Contractor;
import sptech.faztudo.comLOCAL.users.domain.serviceProvider.ServiceProvider;
import sptech.faztudo.comLOCAL.users.domain.users.User;
import sptech.faztudo.comLOCAL.users.repositorys.contractorRepository;
import sptech.faztudo.comLOCAL.users.repositorys.serviceProviderRepository;
import sptech.faztudo.comLOCAL.users.repositorys.userRepository;

import java.util.List;

@RestController
@RequestMapping("/contractor-post")
public class ControllerFavorite {

    @Autowired
    private RepositoryFavorite favoriteRepository;

    @Autowired
    private contractorRepository contractorRepository;

    @Autowired
    private serviceProviderRepository serviceProviderRepository;
    @Autowired
    private userRepository userRepository;


    @PostMapping("/favorite/{id_contractor}/{id_provider}")
    @Operation(summary = "Add Favorite by Id", description = "Adiciona um favorito por id", tags = "BACKOFFICE")
    public ResponseEntity<?> addFavorite (@PathVariable int id_contractor ,@PathVariable int id_provider){
        try {
            User serviceProvider = userRepository.findById(id_provider);
            User contractor = userRepository.findById(id_contractor);
            List<Long> idApagado = favoriteRepository.existsByFk(id_contractor,id_provider);
            if(contractor.getId() > 0 && serviceProvider.getId() > 0 && idApagado.isEmpty()){
            Favorite favorite = new Favorite(id_contractor,id_provider);
            favoriteRepository.save(favorite);}
            else {
                return ResponseEntity.status(404).build();}

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/favorite/{id_contractor}/{id_provider}")
    @Operation(summary = "Remove Favorite by Id", description = "Remove um favorito por id", tags = "BACKOFFICE")
    public ResponseEntity<?> removeFavorite (@PathVariable int id_contractor ,@PathVariable int id_provider){

        List<Long> idApagado = favoriteRepository.existsByFk(id_contractor,id_provider);
        if(!idApagado.isEmpty() ){
            favoriteRepository.deleteById(idApagado.get(0));
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }
}
