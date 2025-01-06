package com.example.expenseapi.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="archived_memberships")
public class ArchivedMembership extends BaseMembership<ArchivedGroup>{
    public ArchivedMembership(User user, ArchivedGroup group, Role role) {
        super(user, group, role);
    }

    public ArchivedMembership(BaseMembership<ArchivedGroup> entity) {
        super(entity.user, entity.group, entity.role);
    }
}
