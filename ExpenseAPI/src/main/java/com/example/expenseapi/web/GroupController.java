package com.example.expenseapi.web;

import com.example.expenseapi.pojo.BaseGroup;
import com.example.expenseapi.repository.MembershipRepository;
import com.example.expenseapi.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController  {
    private final MembershipRepository membershipRepository;
    private final UserService userService;

    public GroupController(MembershipRepository membershipRepository, UserService userService) {
        this.membershipRepository = membershipRepository;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<BaseGroup> getAllGroups(@AuthenticationPrincipal User user) {
        Optional<com.example.expenseapi.pojo.User> mUser = userService.findByEmail(user.getUsername());
        return mUser.map(value -> membershipRepository.findBaseGroupsByUser_Id(value.getId())).orElse(null);
    }
}
