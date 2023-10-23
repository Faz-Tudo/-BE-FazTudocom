package sptech.faztudo.comLOCAL.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.faztudo.comLOCAL.domain.csv.GerenciadorDeArquivo;
import sptech.faztudo.comLOCAL.domain.users.User;
import sptech.faztudo.comLOCAL.repositorys.csvRepository;

import java.util.List;
@RestController

public class CsvController {

    @Autowired
    public csvRepository csvRepository;

    @GetMapping("/csv/save")
    @Operation(summary = "Save CSV", description = "Listar todos os usuarios e salvar um CSV", tags = "BACKOFFICE")
    public ResponseEntity<List<User>> saveCSV() {

        List<User> users = csvRepository.findAll();

        GerenciadorDeArquivo.gravaArquivoCsv(users, "ArquivoCSV");

        return ResponseEntity.status(200).body(users);

    }

    @PostMapping("/csv/read")
    @Operation(summary = "Read CSV", description = "Ler o CSV de todos os usuários", tags = "BACKOFFICE")
    public ResponseEntity<List<User>> readCSV() {

        try {
            List<User> users = GerenciadorDeArquivo.leArquivoCsv("ArquivoCSV");

            return ResponseEntity.status(200).body(users);

        } catch (Exception e) {

            return ResponseEntity.status(400).build();
        }
    }



}
