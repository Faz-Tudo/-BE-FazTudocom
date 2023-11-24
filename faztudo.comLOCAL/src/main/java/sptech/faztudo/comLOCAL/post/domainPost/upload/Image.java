package sptech.faztudo.comLOCAL.post.domainPost.upload;

import jakarta.persistence.*;
import sptech.faztudo.comLOCAL.users.domain.users.User;

@Entity
@Table(name="images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User fkUser;

    @Column(nullable = false)
    private String name;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false, columnDefinition = "longblob")
    private byte[] data;

    public Image() {
    }

    public Image(Long id, User fkUser, String name, byte[] data) {
        this.id = id;
        this.fkUser = fkUser;
        this.name = name;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFkUser() {
        return fkUser;
    }

    public void setFkUser(User fkUser) {
        this.fkUser = fkUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
