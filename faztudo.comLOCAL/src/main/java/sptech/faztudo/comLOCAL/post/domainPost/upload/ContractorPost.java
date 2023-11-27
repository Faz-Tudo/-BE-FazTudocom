package sptech.faztudo.comLOCAL.post.domainPost.upload;

import jakarta.persistence.*;
import sptech.faztudo.comLOCAL.users.domain.users.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "contractor_post")
public class ContractorPost {

    @Id
    @Column(unique = true, name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_contractor")
    private Integer fkContractor;

    @Column(name = "fk_provider")
    private Integer fkProvider;

    @Column
    private String descricao;

    @Column
    private Integer categoria;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataDeConclusao;

    @Column(name = "fk_image")
    private Long foto;



    public ContractorPost() {
    }

    public ContractorPost(Long id,
                          Integer fkContractor,
                          Integer fkProvider,
                          String descricao,
                          Integer categoria,
                          LocalDateTime dataCriacao,
                          LocalDateTime dataDeConclusao,
                          Long foto) {
        this.id = id;
        this.fkContractor = fkContractor;
        this.fkProvider = fkProvider;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dataCriacao = dataCriacao;
        this.dataDeConclusao = dataDeConclusao;
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFkContractor() {
        return fkContractor;
    }

    public void setFkContractor(Integer fkContractor) {
        this.fkContractor = fkContractor;
    }

    public Integer getFkProvider() {
        return fkProvider;
    }

    public void setFkProvider(Integer fkProvider) {
        this.fkProvider = fkProvider;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataDeConclusao() {
        return dataDeConclusao;
    }

    public void setDataDeConclusao(LocalDateTime dataDeConclusao) {
        this.dataDeConclusao = dataDeConclusao;
    }

    public Long getFoto() {
        return foto;
    }

    public void setFoto(Long foto) {
        this.foto = foto;
    }
}
