package tasks.tasksapp.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    private String username;

    @JsonIgnore
    private String pass;

    private String token;

    public User(String username, String pass, String token) {
        this.username = username;
        this.pass = pass;
        this.token = token;
    }
}
