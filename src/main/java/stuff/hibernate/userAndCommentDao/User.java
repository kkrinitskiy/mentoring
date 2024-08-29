package stuff.hibernate.userAndCommentDao;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "h_user")
@NamedQueries({
        @NamedQuery(
                name = "User.FindByFirstName",
                query = "from User where firstName = :firstName"),
        @NamedQuery(
                name = "User.FindByLastName",
                query = "from User where lastName = :lastName"),
        @NamedQuery(
                name = "User.FindById",
                query = "from User where id = :id")
})
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Date birthdate;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Comment> comments;
}
