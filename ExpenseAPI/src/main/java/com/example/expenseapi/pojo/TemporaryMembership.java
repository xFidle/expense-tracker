package com.example.expenseapi.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "temporary_memberships")
public class TemporaryMembership extends BaseMembership<Group> {
    public TemporaryMembership(User user, Group group, Role role) {
        super(user, group, role);
    }

    public TemporaryMembership(BaseMembership<Group> entity) {
        super(entity.user, entity.group, entity.role);
    }
}
