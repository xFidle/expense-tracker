package com.example.expenseapi.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_membership_gen")
    @SequenceGenerator(
            name = "base_membership_gen",
            sequenceName = "base_membership_seq",
            allocationSize = 1
    )
    protected Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    protected User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    protected BaseGroup group;

    @Column(name = "name")
    protected String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    protected Role role;

    public BaseMembership(User user, BaseGroup group, String name, Role role) {
        this.user = user;
        this.group = group;
        this.name = name;
        this.role = role;
    }




}
