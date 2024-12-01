package com.example.expenseapi.pojo;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR2(255)")
    private String name;

    @NonNull
    @Column(name = "surname", nullable = false, columnDefinition = "VARCHAR2(255)")
    private String surname;

    @NonNull
    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR2(255)")
    private String email;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_group_id", referencedColumnName = "id", nullable = false)
    private UserGroup userGroup;

    public User(String username, String surname, String email, UserGroup userGroup) {
        this.name = username;
        this.surname = surname;
        this.email = email;
        this.userGroup = userGroup;
    }
}
