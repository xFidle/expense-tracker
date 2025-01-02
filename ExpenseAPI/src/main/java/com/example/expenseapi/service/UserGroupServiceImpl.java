package com.example.expenseapi.service;

import com.example.expenseapi.pojo.Group;
import com.example.expenseapi.pojo.User;
import com.example.expenseapi.repository.GroupRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserGroupServiceImpl extends GenericServiceImpl<Group, Long> implements UserGroupService{
    private final UserService userService;
    private final MembershipService membershipService;
    public UserGroupServiceImpl(GroupRepository repository, UserService userService, MembershipService membershipService) {
        super(repository);
        this.userService = userService;
        this.membershipService = membershipService;
    }

    @Override
    public List<Group> getAll() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userService.findByEmail(email);
        return user.map(value ->
                membershipService.getBaseGroupsByUserId(value.getId()).stream()
                        .filter(baseGroup -> baseGroup instanceof Group)
                        .map(baseGroup -> (Group) baseGroup)
                        .collect(Collectors.toList())
        ).orElse(Collections.emptyList());
    }
}
