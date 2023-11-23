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

    @ManyToOne
    @JoinColumn(name = "fk_contractor")
    private User fkContractor;

    @ManyToOne
    @JoinColumn(name = "fk_provider", nullable = true)
    private User fkProvider;

    @Column
    private String descricao;

    @Column
    private String categoria;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_conclusao", nullable = true)
    private LocalDateTime dataDeConclusao;

    @ManyToOne
    @JoinColumn(name = "fk_image")
    private Image foto;



    public ContractorPost() {
    }

    public ContractorPost(Long id, User fkContractor, User fkProvider, String descricao, String categoria, LocalDateTime dataCriacao, LocalDateTime dataDeConclusao, Image foto) {
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

    public User getFkContractor() {
        return fkContractor;
    }

    public void setFkContractor(User fkContractor) {
        this.fkContractor = fkContractor;
    }

    public User getFkProvider() {
        return fkProvider;
    }

    public void setFkProvider(User fkProvider) {
        this.fkProvider = fkProvider;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
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

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }
}
